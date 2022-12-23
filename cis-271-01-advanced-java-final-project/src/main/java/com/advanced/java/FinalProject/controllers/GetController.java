package com.advanced.java.FinalProject.controllers;

import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.exceptions.BadEntityRequest;
import com.advanced.java.FinalProject.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GetController {

    private final PatientService patientService;
    @Autowired
    GetController(PatientService patientService){this.patientService = patientService;}
    @GetMapping (value = "getPatientById")
    public Optional<Patient> getPatientById(@RequestParam Long patientId) throws BadEntityRequest {
        System.out.println("Calling the getPatientById() endpoint");
        return patientService.getPatientById(patientId);
    }
//    @GetMapping (value = "getDietitianByPatientId")
//    public Dietitian getDietitianByPatientId(@RequestParam Long patientId) throws BadEntityRequest {
//        System.out.println("Calling the getDietitianByPatientId() endpoint");
//        return dietitianService.getDietitianByPatientId(patientId);
//    }
}
