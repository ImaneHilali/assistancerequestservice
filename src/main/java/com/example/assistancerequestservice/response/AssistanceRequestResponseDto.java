package com.example.assistancerequestservice.response;

import com.example.assistancerequestservice.dto.PointDto;
import com.example.assistancerequestservice.serialization.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Point;

@Data
public class AssistanceRequestResponseDto {

    private Long id;
    private String fullname;
    private String phone;

    @JsonSerialize(using= PointSerializer.class)
    private Point localisation;

    private String address;
    private String expressNeeds;

}
