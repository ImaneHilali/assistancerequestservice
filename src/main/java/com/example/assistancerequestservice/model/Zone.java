package com.example.assistancerequestservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.Data;

import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;
import java.util.List;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

@Data
@Entity(name = "zone")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "geometry(Polygon,4326)")
    private Polygon geometry;

    @ManyToOne
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("zone")
    private List<AssistanceRequest> assistanceRequests = new ArrayList<>();


}