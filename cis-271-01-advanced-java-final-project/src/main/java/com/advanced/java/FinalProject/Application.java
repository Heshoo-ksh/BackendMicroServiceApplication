package com.advanced.java.FinalProject;

import com.advanced.java.FinalProject.entities.Patient;
import com.advanced.java.FinalProject.entities.Room;
import com.advanced.java.FinalProject.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public CommandLineRunner runPatient(PatientRepository patientRepository) throws Exception {
//
//		return (String[] args) -> {
//
//			Patient patient = new Patient("Hisham", "Odeh", "K", "23453456", 21L, 72L, 140L, "11/11/2000" , "couf", "cardiac");
//
//			Room room = new Room("123", "single",patient);
//			Room room1 = new Room("321", "double",patient);
//			Set<Room> rooms = new HashSet<>();
//			rooms.add(room);
//			rooms.add(room1);
//			patient.setRooms(rooms);
//			patientRepository.save(patient);
//			patientRepository.findAll().forEach(System.out::println);
//
//		};
//	}
}
