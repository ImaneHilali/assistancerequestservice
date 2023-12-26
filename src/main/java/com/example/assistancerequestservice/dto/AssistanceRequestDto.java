package com.example.assistancerequestservice.dto;

import lombok.Data;

@Data
public class AssistanceRequestDto {

    private Long id;

    private String fullname;

    private String phone;

    private PointDto localisation;

    private String address;

    private String expressNeeds;

    private Long DisasterId;
}
