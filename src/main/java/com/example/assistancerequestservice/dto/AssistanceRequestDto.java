package com.example.assistancerequestservice.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
public class AssistanceRequestDto {

    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullname;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotNull(message = "Localisation is required")
    private PointDto localisation;

    @NotBlank(message = "Address is required")
    private String address;

    private String expressNeeds;

    @NotNull(message = "Disaster ID is required")
    private Long DisasterId;
}
