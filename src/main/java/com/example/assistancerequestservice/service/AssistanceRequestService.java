package com.example.assistancerequestservice.service;

import com.example.assistancerequestservice.dto.AssistanceRequestDto;
import com.example.assistancerequestservice.model.AssistanceRequest;
import com.example.assistancerequestservice.model.Disaster;
import com.example.assistancerequestservice.model.Zone;
import com.example.assistancerequestservice.response.AssistanceRequestResponseDto;
import org.locationtech.jts.geom.Point;

import java.util.List;

public interface AssistanceRequestService {
    public AssistanceRequest createAssistanceRequest(AssistanceRequestDto assistanceRequestDto);
    public AssistanceRequestResponseDto getAssistanceRequest(Long id);
    public Disaster getDisaster(Long id);
    public Zone findAssociatedZone(Point localisation, List<Zone> zones);
    public boolean isPointInside(Point point, Zone zone);

    }
