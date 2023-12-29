package com.example.assistancerequestservice.dto;

import lombok.Data;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

@Data
public class PointDto {

    @DecimalMin(value = "-90.0", message = "Latitude must be greater than or equal to -90")
    @DecimalMax(value = "90.0", message = "Latitude must be less than or equal to 90")
    private double latitude;

    @DecimalMin(value = "-180.0", message = "Longitude must be greater than or equal to -180")
    @DecimalMax(value = "180.0", message = "Longitude must be less than or equal to 180")
    private double longitude;
}
