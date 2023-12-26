package com.example.assistancerequestservice.repository;

import com.example.assistancerequestservice.model.AssistanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistanceRequestRepository extends JpaRepository<AssistanceRequest,Long> {
}
