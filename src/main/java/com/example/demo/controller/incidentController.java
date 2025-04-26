package com.example.demo.controller;

import com.example.demo.model.Incident;

import com.example.demo.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/incidents")
public class incidentController {

    private final IncidentRepository incidentRepository;

    private static final List<String> VALID_SEVERITIES = List.of("Low", "Medium", "High");

    @Autowired
    public incidentController(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Incident>> getAllIncidents() {
        List<Incident> incidents = incidentRepository.findAll();
        return ResponseEntity.ok(incidents);
    }


    @PostMapping("/save")
    public ResponseEntity<?> createIncident(@RequestBody Incident incident) {
        if (incident.getTitle() == null || incident.getDescription() == null || incident.getSeverity() == null) {
            return ResponseEntity.badRequest().body("Title, description, and severity are required.");
        }

        if (!VALID_SEVERITIES.contains(incident.getSeverity())) {
            return ResponseEntity.badRequest().body("Severity must be one of: Low, Medium, or High.");
        }

        incident.setReportedAt(LocalDateTime.now());

        Incident savedIncident = incidentRepository.save(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIncident);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getIncidentById(@PathVariable Long id) {
        Optional<Incident> incidentOpt = incidentRepository.findById(id);

        if (incidentOpt.isPresent()) {
            return ResponseEntity.ok(incidentOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Incident not found with ID: " + id);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncident(@PathVariable Long id) {
        if (!incidentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incident not found with ID: " + id);
        }

        incidentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
