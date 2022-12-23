package com.advanced.java.FinalProject;

import com.advanced.java.FinalProject.entities.Dietitian;
import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.entities.Room;
import com.advanced.java.FinalProject.exceptions.BadEntityRequest;
import com.advanced.java.FinalProject.services.DatabaseService;
import com.advanced.java.FinalProject.services.DietitianService;
import com.advanced.java.FinalProject.services.PatientService;
import com.advanced.java.FinalProject.services.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
//import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DietitianServiceTest {
    DietitianService dietitianService;
    PatientService patientService;

    @Mock
    DatabaseService databaseService;

    private Dietitian dietitian;
    private Patient patient;
    @BeforeEach
    void setUp() {
        patientService = new PatientService(databaseService);
        dietitianService = new DietitianService(databaseService);

        patient = new Patient("Hisham", "Odeh", "K", "23453456", 21L, 72L, 140L, "11/11/2000" , "couf", "cardiac");
        patient.setId(1L);
        dietitian = new Dietitian(160L,20L,"chest",true,"Doctor, H",patient);
    }

    @Test
    void saveDietitian_addsDietitianToTheDataBaseAndConnectsItToaDietitian() throws BadEntityRequest {

        Dietitian returnedDietitian = dietitian;
        Patient returnedPatient = patient;
        when(databaseService.getPatientById(1L)).thenReturn((returnedPatient));
        when(databaseService.saveDietitian(dietitian)).thenReturn(returnedDietitian);

        Dietitian expected = dietitian;
        Dietitian actual = dietitianService.saveDietitian(dietitian,patient.getId());
        assertThat(expected, is(equalTo(actual)));
    }

    @Test
    void getDietitianByPatientId_returns_dietitian_basedOnPatientId() throws BadEntityRequest {
        Dietitian returnedDietitian = dietitian;

        when(databaseService.getDietitianByPatientId(patient.getId())).thenReturn(returnedDietitian);

        Dietitian expected = dietitian;
        Dietitian actual = dietitianService.getDietitianByPatientId(patient.getId());

        assertThat(expected, is(equalTo(actual)));
    }

    @Test
    void updateDietitianByPatientId() throws BadEntityRequest {
        Dietitian returnedDietitian = dietitian;
        Patient returnedPatient = patient;
        when(databaseService.getPatientByIdOptional(1L)).thenReturn(Optional.of(returnedPatient));
        when(databaseService.saveDietitian(dietitian)).thenReturn(returnedDietitian);
        when(databaseService.getDietitianByPatientId(patient.getId())).thenReturn(returnedDietitian);

        Dietitian expected = dietitian;
        Dietitian actual = dietitianService.updateDietitianByPatientId(dietitian,patient.getId());

        assertThat(expected, is(equalTo(actual)));
    }
    @Test
    void deleteDietitianById_deletesADietitianByPatientIdFromTheDAtaBase(){
        dietitianService.deleteDietitianById(1L);
        Mockito.verify(databaseService).deleteDietitianById(patient.getId());
    }
    @Test
    void saveDietitian_clearsTheDietitianSet() throws BadEntityRequest {
        Dietitian returnedDietitian = dietitian;
        Patient returnedPatient = patient;
        Dietitian dietitian1 = new Dietitian(170L,20L,"back",false,"Doctor, H",patient);

        when(databaseService.savePatient(patient)).thenReturn(returnedPatient);
        when(databaseService.getPatientById(1L)).thenReturn((returnedPatient));
        when(databaseService.saveDietitian(dietitian)).thenReturn(returnedDietitian);
        when(databaseService.getDietitianByPatientId(patient.getId())).thenReturn(returnedDietitian);

        Set<Dietitian> dietitians = new HashSet<>();
        dietitians.add(dietitian1);
        dietitians.add(dietitian);
        returnedPatient.setDietitians(dietitians);

        returnedPatient = patientService.savePatient(returnedPatient);
        dietitianService.saveDietitian(returnedDietitian,1L);

        assert (returnedPatient.getDietitians().size() == 0);
    }


}
