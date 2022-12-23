package com.advanced.java.FinalProject.controllers;

import com.advanced.java.FinalProject.exceptions.BadEntityRequest;
import com.advanced.java.FinalProject.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {

    private final PatientService patientService;

    @Autowired
    DeleteController(PatientService patientService){
        this.patientService = patientService;
    }
    @DeleteMapping( value = "deletePatientByPatientId")
    public void deletePatientByPatientId(Long patientId) throws BadEntityRequest {
        System.out.println("Called deletePatientByPatientId(). Deleting Patient with Id:" + patientId);
        patientService.deletePatientByPatientId(patientId);
    }

}