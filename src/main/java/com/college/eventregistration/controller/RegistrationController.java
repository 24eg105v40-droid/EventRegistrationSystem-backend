package com.college.eventregistration.controller;

import com.college.eventregistration.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Remove a participant registration by registration ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> removeRegistration(@PathVariable Long id) {
        registrationService.removeRegistration(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Participant registration successfully removed."));
    }
}
