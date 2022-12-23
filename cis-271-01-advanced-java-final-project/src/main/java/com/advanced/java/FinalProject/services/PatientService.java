package com.advanced.java.FinalProject.services;

import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.exceptions.BadEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final DatabaseService databaseService;

    @Autowired
    public PatientService (DatabaseService databaseService){ this.databaseService = databaseService;}

    public Patient savePatient(Patient patient) throws BadEntityRequest {
        patient.setDiagnosis(patient.getDiagnosis().toUpperCase());

        List<String> diagnosisList = List.of( "CARDIAC","PULMONARY","UROLOGICAL","ENDOCRIN","SKELETAL","MUSCULAR","DIGESTIVE");

        if ( patient.getFirstName().isEmpty() || patient.getFirstName().isBlank())
            throw new BadEntityRequest ("Patient first name is a required field.");
        if (patient.getLastName().isEmpty() || patient.getLastName().isBlank())
            throw new BadEntityRequest("Patient last name is a required filed");
        if (patient.getMiddleName().isBlank() || patient.getMiddleName().isEmpty())
            throw new BadEntityRequest("patient middle name is a required field");
        if (patient.getSsn().isEmpty() || patient.getSsn().isBlank())
            throw new BadEntityRequest("patient SSN is a required field");
        if (patient.getDateOfBirth().isBlank() || patient.getDateOfBirth().isEmpty())
            throw new BadEntityRequest("Date of birth is a required field.");
        if (patient.getSymptoms().isEmpty() || patient.getSymptoms().isBlank())
            throw new BadEntityRequest("Patient symptoms is a required field.");
        if (patient.getDiagnosis().isBlank() || patient.getDiagnosis().isEmpty())
            throw new BadEntityRequest("Patient diagnose in a required filed");

        if (patient.getAge() == null)
            throw new BadEntityRequest("Patient age is a required field");
        if (patient.getHeight() == null)
            throw new BadEntityRequest("Patient height is a required field");
        if (patient.getWeight() == null)
            throw new BadEntityRequest("Patient weight is a required field");

        if (patient.getAge() <= 0)
            throw new BadEntityRequest("Patient age is invalid, must be grater than zero");
        if (patient.getHeight() <= 0)
            throw new BadEntityRequest("Patient height is invalid, must be grater than zero");
        if (patient.getWeight() <= 0)
            throw new BadEntityRequest("Patient weight is invalid, must be grater than zero");

        if (!diagnosisList.contains(patient.getDiagnosis()))
            throw new BadEntityRequest("Patient diagnose in not valid, diagnose must be among " + diagnosisList);

        //validates date format and value
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.setLenient(false);
            format.parse(patient.getDateOfBirth());
        } catch (ParseException e) {
            throw new BadEntityRequest("Date format is not valid");
        }

        return databaseService.savePatient(patient);
    }

    public Optional<Patient> getPatientById(Long patientId) throws BadEntityRequest {

        if (databaseService.getPatientByIdOptional(patientId).isEmpty())
            throw new BadEntityRequest("Patient with the id "  + patientId +" does not exist");
        return databaseService.getPatientByIdOptional(patientId);
    }

    public void deletePatientByPatientId(Long patientId) throws BadEntityRequest {
        if(databaseService.getPatientByIdOptional(patientId).isEmpty())
            throw new BadEntityRequest("The patient with id " + patientId + " doesn't exist.  Delete failed.");
        databaseService.deletePatientByPatientId(patientId);
    }
}
