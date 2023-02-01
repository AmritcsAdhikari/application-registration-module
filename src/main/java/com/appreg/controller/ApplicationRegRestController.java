package com.appreg.controller;


import com.appreg.dto.ApplicantDtlDto;
import com.appreg.service.ApplicationRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register-application")
public class ApplicationRegRestController {
    @Autowired
    private ApplicationRegistrationService appRegService;

    @PostMapping("/register")
    public ResponseEntity<String> createApplication(@RequestBody ApplicantDtlDto applicantDtlDto) {

        String response = appRegService.createApplication(applicantDtlDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
