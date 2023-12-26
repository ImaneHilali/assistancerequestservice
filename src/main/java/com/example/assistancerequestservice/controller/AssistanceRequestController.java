package com.example.assistancerequestservice.controller;

import com.example.assistancerequestservice.dto.AssistanceRequestDto;
import com.example.assistancerequestservice.model.AssistanceRequest;
import com.example.assistancerequestservice.response.AssistanceRequestResponseDto;
import com.example.assistancerequestservice.service.AssistanceRequestService;
import com.example.assistancerequestservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assistance-requests")
public class AssistanceRequestController {

    private final AssistanceRequestService assistanceRequestService;

    private final UserService userService;

    @Value("${other.service.url}")
    private String authService;

    @Autowired
    public AssistanceRequestController(AssistanceRequestService assistanceRequestService, UserService userService) {
        this.assistanceRequestService = assistanceRequestService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<AssistanceRequestResponseDto> createAssistanceRequest(@RequestBody AssistanceRequestDto assistanceRequestDto) {
        AssistanceRequest createdAssistanceRequest = assistanceRequestService.createAssistanceRequest(assistanceRequestDto);
        AssistanceRequestResponseDto responseDto = assistanceRequestService.getAssistanceRequest(createdAssistanceRequest.getId());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssistanceRequest(@PathVariable Long id, @RequestHeader("Authorization") String token) {

        Boolean isOrganization = userService.isOrganization(token,authService);


        if(isOrganization) {
            AssistanceRequestResponseDto assistanceRequestResponseDto = assistanceRequestService.getAssistanceRequest(id);
            return new ResponseEntity<>(assistanceRequestResponseDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Authorized",HttpStatus.UNAUTHORIZED);
    }


}