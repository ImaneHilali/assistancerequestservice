package com.example.assistancerequestservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
    @JsonProperty("disasterId")
    private Long disasterId;
}
