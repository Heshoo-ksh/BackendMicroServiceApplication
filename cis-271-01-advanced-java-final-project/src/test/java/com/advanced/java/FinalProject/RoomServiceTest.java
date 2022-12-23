package com.advanced.java.FinalProject;

import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.entities.Room;
import com.advanced.java.FinalProject.exceptions.BadEntityRequest;
import com.advanced.java.FinalProject.services.DatabaseService;
import com.advanced.java.FinalProject.services.PatientService;
import com.advanced.java.FinalProject.services.RoomService;
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
public class RoomServiceTest {
    RoomService roomService;
    PatientService patientService;

    @Mock
    DatabaseService databaseService;

    private Room room;
    private Patient patient;
    @BeforeEach
    void setUp() {
        patientService = new PatientService(databaseService);
        roomService = new RoomService(databaseService);

        patient = new Patient("Hisham", "Odeh", "K", "23453456", 21L, 72L, 140L, "11/11/2000" , "couf", "cardiac");
        patient.setId(1L);
        room = new Room("123", "single",patient);

    }

    @Test
    void saveRoom_addsRoomToTheDataBaseAndConnectsItToaPatient() throws BadEntityRequest {

        Room returnedRoom = room;
        Patient returnedPatient = patient;
        when(databaseService.getPatientByIdOptional(1L)).thenReturn(Optional.of(returnedPatient));
        when(databaseService.saveRoom(room)).thenReturn(returnedRoom);

        Room expected = room;
        Room actual = roomService.saveRoom(room, patient.getId());
        assertThat(expected, is(equalTo(actual)));
    }
    @Test
    void diagnoseIsAlwaysSetToUpperCase() throws BadEntityRequest {
        Room returnedRoom = room;

        when(databaseService.getPatientByIdOptional(1L)).thenReturn(Optional.of(patient));
        when(databaseService.saveRoom(room)).thenReturn(returnedRoom);

        roomService.saveRoom(returnedRoom, patient.getId());
        assert("SINGLE".equals(returnedRoom.getRoomType()));
    }


}
