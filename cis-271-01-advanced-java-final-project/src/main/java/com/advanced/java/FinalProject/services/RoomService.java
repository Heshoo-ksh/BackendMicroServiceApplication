package com.advanced.java.FinalProject.services;

import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.entities.Room;
import com.advanced.java.FinalProject.exceptions.BadEntityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoomService {
    private final DatabaseService databaseService;

    @Autowired
    public RoomService (DatabaseService databaseService){ this.databaseService = databaseService;}

    public Room saveRoom(Room room, Long patientId) throws BadEntityRequest {

        if (databaseService.getPatientByIdOptional(patientId).isEmpty())
            throw new BadEntityRequest("Patient with the id of ["+ patientId +"] does not exist. Enter a valid patient_id. Room was not added!");
        room.setRoomType(room.getRoomType().toUpperCase());
        List<String> roomType = List.of("SINGLE","DOUBLE","MULTIPLE","WARD","ICU","EMERGENCY");

        if (room.getRoomNumber().isEmpty() || room.getRoomNumber().isBlank())
            throw new BadEntityRequest("Room number is a required field");
        if (!roomType.contains(room.getRoomType()))
            throw new BadEntityRequest("Invalid room type, room type must be among:\t" + roomType);
        if (room.getRoomType().isEmpty() || room.getRoomType().isBlank())
            throw new BadEntityRequest("Room type is a required field");

        Patient patient = databaseService.getPatientById(patientId);
        room.setPatient(patient);

        return databaseService.saveRoom(room);
    }

}
