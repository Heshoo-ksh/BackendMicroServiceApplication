package com.advanced.java.FinalProject.controllers;

import com.advanced.java.FinalProject.entities.Dietitian;
import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.entities.Room;
import com.advanced.java.FinalProject.exceptions.BadEntityRequest;
import com.advanced.java.FinalProject.services.DatabaseService;
import com.advanced.java.FinalProject.services.DietitianService;
import com.advanced.java.FinalProject.services.PatientService;
import com.advanced.java.FinalProject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {

    private final PatientService patientService;
    private final RoomService roomService;
    private final DietitianService dietitianService;

    @Autowired
    PostController(PatientService patientService, RoomService roomService, DietitianService dietitianService){
        this.patientService = patientService;
        this.roomService = roomService;
        this.dietitianService = dietitianService;
    }

    @PostMapping (value = "savePatient")
    public Patient savePatient(@RequestBody Patient patient) throws BadEntityRequest {
        System.out.println("Adding the following new patient:\t" + patient.toString());
        return patientService.savePatient(patient);
    }
    @PostMapping (value = "saveRoom")
    public Room saveRoom (@RequestBody Room room, @RequestParam Long patientId) throws BadEntityRequest {
        System.out.println("Adding the following new room:\t" + room.toString() + " to the patent with the ID of:\t" + patientId );
        return roomService.saveRoom(room, patientId);
    }
    @PostMapping (value = "saveDietitian")
    public Dietitian dietitian (@RequestBody Dietitian dietitian, @RequestParam Long patientId) throws BadEntityRequest {
        System.out.println("Adding the following new dietitian:\t" + dietitian.toString() + " to the patent with the ID of:\t" +patientId );
        return dietitianService.saveDietitian(dietitian, patientId);
    }

}
