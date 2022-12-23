package com.advanced.java.FinalProject.controllers;

import com.advanced.java.FinalProject.entities.Dietitian;
import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.exceptions.BadEntityRequest;
import com.advanced.java.FinalProject.services.DietitianService;
import com.advanced.java.FinalProject.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PatchController {

    private final DietitianService dietitianService;

    @Autowired
    PatchController(DietitianService dietitianService){this.dietitianService = dietitianService;}
    @PatchMapping (value = "updateDietitianByPatientId")
    public Dietitian updateDietitianByPatientId(@RequestBody Dietitian dietitian, @RequestParam Long patientId) throws BadEntityRequest {
        System.out.println("Updating the following Dietitian: " + dietitian + " with patientId: " + patientId);
        return dietitianService.updateDietitianByPatientId(dietitian,patientId);
    }


}