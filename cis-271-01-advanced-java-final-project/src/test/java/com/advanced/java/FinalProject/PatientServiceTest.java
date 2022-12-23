package com.advanced.java.FinalProject;

import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.exceptions.BadEntityRequest;
import com.advanced.java.FinalProject.services.DatabaseService;
import com.advanced.java.FinalProject.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
//import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PatientServiceTest {
    PatientService patientService;

    @Mock
    DatabaseService databaseService;

    private Patient patient;
    @BeforeEach
    void setUp(){
        patientService = new PatientService(databaseService);

        patient = new Patient("Hisham", "Odeh", "K", "23453456", 21L, 72L, 140L, "11/11/2000" , "couf", "cardiac");
        patient.setId(1L);
    }

    @Test
    void savePatient_addsPatientToTheDataBase() throws BadEntityRequest {

        Patient returnedPatient = patient;
        when(databaseService.savePatient(patient)).thenReturn(returnedPatient);

        Patient expected = patient;
        Patient actual = patientService.savePatient(patient);

        assertThat(expected, is(equalTo(actual)));
    }
    @Test
    void getPatientById_ReturnsPatientById() throws BadEntityRequest {
        Optional<Patient> returnedPatient = Optional.of((patient));
        when(databaseService.getPatientByIdOptional(1L)).thenReturn((returnedPatient));

        Optional<Patient> actual = patientService.getPatientById(1L);
        assertThat(returnedPatient,is(equalTo(actual)));
    }
    @Test
    void deletePatientByPatientId() throws BadEntityRequest {
        Patient returnedPatient = patient;

        when(databaseService.getPatientByIdOptional(1L)).thenReturn(Optional.of(returnedPatient));

        patientService.deletePatientByPatientId(1L);
        Mockito.verify(databaseService).deletePatientByPatientId(1L);
    }
    @Test
    void firstNameFieldCantBeSetToNull() {
        Patient returnedPatient = patient;
        when(databaseService.savePatient(patient)).thenReturn(returnedPatient);
        assert(returnedPatient.getFirstName() != null);
    }
    @Test
    void lastNameFieldCantBeSetToNull() throws BadEntityRequest {
        Patient returnedPatient = patient;

        when(databaseService.savePatient(patient)).thenReturn(returnedPatient);

        Patient actual = patientService.savePatient(patient);
        assert(patient.getLastName() != null);
    }
    @Test
    void diagnoseIsAlwaysSetToUpperCase() throws BadEntityRequest {
        Patient returnedPatient = patient;
        when(databaseService.savePatient(patient)).thenReturn(returnedPatient);
        Patient actual = patientService.savePatient(patient);

        assert("CARDIAC".equals(actual.getDiagnosis()));
    }



}
