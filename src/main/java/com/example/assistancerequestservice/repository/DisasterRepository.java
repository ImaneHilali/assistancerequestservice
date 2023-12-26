package com.example.assistancerequestservice.repository;

import com.example.assistancerequestservice.model.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisasterRepository extends JpaRepository<Disaster,Long> {
}
