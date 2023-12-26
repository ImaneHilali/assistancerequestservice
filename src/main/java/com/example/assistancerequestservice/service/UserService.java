package com.example.assistancerequestservice.service;

import com.example.assistancerequestservice.dto.UserDetailsDto;

public interface UserService {
    public UserDetailsDto getUserDetailsFromOtherService(String serviceUrl, String token);


    public boolean isAdmin(String token, String url);
    public boolean isOrganization(String token, String url);
}