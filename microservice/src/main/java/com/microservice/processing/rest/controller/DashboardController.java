package com.microservice.processing.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
public class DashboardController {

    /**
     * Gets all summary.
     */
    @Operation(summary = "Gets all summary.")
    @PreAuthorize("hasRole('ROLE_TECH_SUPPORT') or hasRole('ROLE_EDITOR')")
    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSummary()
    {
        return ResponseEntity.ok("REST API Result");
    }

    /**
     * Gets all summary.
     */
    @Operation(summary = "Gets all summary.")
    @PreAuthorize("hasRole('ROLE_TECH_SUPPORT') or hasRole('ROLE_EDITOR')")
    @GetMapping(value = "/test_sec", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSecSummary()
    {
        Map data = new HashMap<>();
        data.put("msg", "REST API Sec Result");
        return
                ResponseEntity.ok(data);
    }
}
