package com.advanced.java.FinalProject.services;


import com.advanced.java.FinalProject.entities.Dietitian;
import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.exceptions.BadEntityRequest;
import com.advanced.java.FinalProject.repositories.DietitianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.lang.Math.abs;


@Service
public class DietitianService {
    private final DatabaseService databaseService;

    @Autowired
    public DietitianService (DatabaseService databaseService){this.databaseService = databaseService;}

    public Dietitian saveDietitian(Dietitian dietitian, Long patientId) throws BadEntityRequest {
        if (databaseService.getPatientById(patientId) == null)
            throw new BadEntityRequest("Patient with the if of ["+ patientId +"] does not exist. Enter a valid patient_id. Dietitian was not added");

        if (dietitian.getCurrentWeight() == null)
            throw new BadEntityRequest("Patients current weight is a required field");
        if (dietitian.getWeightDifferance() == null)
            throw new BadEntityRequest("Patients weight differance is a required field");
        if (dietitian.getWoundLocation() == null || dietitian.getWoundLocation().isBlank() )
            throw new BadEntityRequest("Patients wound location is a required field");
        if (dietitian.getDoctorName() == null || dietitian.getDoctorName().isBlank())
            throw new BadEntityRequest("Patients Doctor Name is a required field");
        if (dietitian.getCurrentWeight() <= 0)
            throw new BadEntityRequest("Patients current weight is not valid.");
        if (!dietitian.getDoctorName().contains(", "))
            throw  new BadEntityRequest("Doctor's name must be is the following format:\t [FirstName, Initial]\tExample:\t[Doctor, E] ");

        if (databaseService.getPatientById(patientId).getDietitians().size() > 0)
        {
            databaseService.getPatientById(patientId).getDietitians().clear();
            databaseService.deleteDietitianById(databaseService.getDietitianByPatientId(patientId).getId());
        }


        Patient patient = databaseService.getPatientById(patientId);
        dietitian.setPatient(patient);
        return databaseService.saveDietitian(dietitian);
    }

    public Dietitian updateDietitianByPatientId(Dietitian dietitian, Long patientId) throws BadEntityRequest {

        if (databaseService.getPatientByIdOptional(patientId).isEmpty())
            throw new BadEntityRequest("Patient with the if of ["+ patientId +"] does not exist. Enter a valid patient_id. Dietitian was not updated");
        if (databaseService.getDietitianByPatientId(patientId) == null)
            throw new BadEntityRequest("Dietitian with patient id of:\t" + patientId +" does not exist. Enter a valid patient_Id");

        Patient patient = databaseService.getPatientById(patientId);
        dietitian.setPatient(patient);
        Dietitian dietitian1 = databaseService.getDietitianByPatientId(patientId);
        if (dietitian.getCurrentWeight() == null)
            dietitian.setCurrentWeight(dietitian1.getCurrentWeight());
        if (dietitian.getWeightDifferance() == null)
            dietitian.setWeightDifferance(abs(dietitian.getCurrentWeight()-dietitian1.getCurrentWeight()));
        if (dietitian.getDoctorName() == null || dietitian.getDoctorName().isBlank())
            dietitian.setDoctorName(dietitian1.getDoctorName());
        if (dietitian.getWoundLocation() == null || dietitian.getWoundLocation().isBlank())
            dietitian.setWoundLocation(null);
        if (!dietitian.getDoctorName().contains(", "))
            throw  new BadEntityRequest("Doctor's name must be is the following format:\t [FirstName, Initial]\tExample:\t[Doctor, E] ");
        if (dietitian.getCurrentWeight() <= 0)
            throw new BadEntityRequest("Patients current weight is not valid.");

        databaseService.deleteDietitianById(databaseService.getDietitianByPatientId(patientId).getId());

        return databaseService.saveDietitian(dietitian);
    }

    public Dietitian getDietitianByPatientId(Long patientId) throws BadEntityRequest {
        if (databaseService.getDietitianByPatientId(patientId) == null)
            throw new BadEntityRequest("Dietitian with patient id of:\t" + patientId +" does not exist");
        return databaseService.getDietitianByPatientId(patientId);
    }

    public void deleteDietitianById(Long id) {databaseService.deleteDietitianById(id);}
}
