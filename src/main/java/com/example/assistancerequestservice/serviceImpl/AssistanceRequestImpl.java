package com.example.assistancerequestservice.serviceImpl;

import com.example.assistancerequestservice.dto.AssistanceRequestDto;
import com.example.assistancerequestservice.exception.DisasterNotFoundException;
import com.example.assistancerequestservice.model.AssistanceRequest;
import com.example.assistancerequestservice.model.Disaster;
import com.example.assistancerequestservice.model.Zone;
import com.example.assistancerequestservice.repository.AssistanceRequestRepository;
import com.example.assistancerequestservice.repository.DisasterRepository;
import com.example.assistancerequestservice.response.AssistanceRequestResponseDto;
import com.example.assistancerequestservice.dto.PointDto;
import com.example.assistancerequestservice.exception.AssistanceRequestNotFoundException;
import com.example.assistancerequestservice.exception.*;
import com.example.assistancerequestservice.service.AssistanceRequestService;
import jakarta.transaction.Transactional;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.locationtech.jts.geom.Point;


import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AssistanceRequestImpl implements AssistanceRequestService {

    final AssistanceRequestRepository assistanceRequestRepository;

    final DisasterRepository disasterRepository;

    public AssistanceRequestImpl(AssistanceRequestRepository assistanceRequestRepository, DisasterRepository disasterRepository) {
        this.assistanceRequestRepository = assistanceRequestRepository;
        this.disasterRepository = disasterRepository;
     }

    public Disaster getDisaster(Long id) {
        return disasterRepository.findById(id).orElseThrow(
                DisasterNotFoundException::new);
    }


    @Transactional
    @Override
    public AssistanceRequest createAssistanceRequest(AssistanceRequestDto assistanceRequestDto) {
        try {
            AssistanceRequest assistanceRequest = new AssistanceRequest();

            PointDto pointDto = assistanceRequestDto.getLocalisation();
            GeometryFactory geometryFactory = new GeometryFactory();
            Point point = geometryFactory.createPoint(new Coordinate(pointDto.getLongitude(), pointDto.getLatitude()));

            assistanceRequest.setFullname(assistanceRequestDto.getFullname());
            assistanceRequest.setPhone(assistanceRequestDto.getPhone());
            assistanceRequest.setAddress(assistanceRequestDto.getAddress());
            assistanceRequest.setLocalisation(point);
            assistanceRequest.setExpressNeeds(assistanceRequestDto.getExpressNeeds());


            Disaster disaster = getDisaster(assistanceRequestDto.getDisasterId());

            List<Zone> zones = disaster.getZones();


            for(Zone zone : zones){
                System.out.println(zone.getId());
            }

            Zone associatedZone = findAssociatedZone(assistanceRequest.getLocalisation(), zones);


            assistanceRequest.setZone(associatedZone);

            return assistanceRequestRepository.save(assistanceRequest);
        } catch (NoSuchElementException e) {
            throw new DisasterNotFoundException();
        }
    }

    public Zone findAssociatedZone(Point localisation, List<Zone> zones) {
        for (Zone zone : zones) {
            if (this.isPointInside(localisation,zone)) {
                return zone;
            }
        }
        throw new ZoneNotFoundException();
    }

    public boolean isPointInside(Point point, Zone zone) {
        Geometry zoneGeometry = zone.getGeometry();
        return zoneGeometry != null && zoneGeometry.contains(point);
    }

    public AssistanceRequestResponseDto getAssistanceRequest(Long id) {
        try {
            AssistanceRequest assistanceRequest = assistanceRequestRepository.findById(id)
                    .orElseThrow(AssistanceRequestNotFoundException::new);

            Disaster disaster = getDisaster(assistanceRequest.getZone().getId());

            List<Zone> zones = disaster.getZones();
            Zone associatedZone = findAssociatedZone(assistanceRequest.getLocalisation(), zones);

            if (associatedZone == null) {
                throw new ZoneNotFoundException();
            }
            assistanceRequest.setZone(associatedZone);

            AssistanceRequestResponseDto assistanceRequestResponseDto = new AssistanceRequestResponseDto();

            assistanceRequestResponseDto.setLocalisation(assistanceRequest.getLocalisation());

            BeanUtils.copyProperties(assistanceRequest, assistanceRequestResponseDto);

            return assistanceRequestResponseDto;
        } catch (AssistanceRequestNotFoundException | DisasterNotFoundException | ZoneNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
