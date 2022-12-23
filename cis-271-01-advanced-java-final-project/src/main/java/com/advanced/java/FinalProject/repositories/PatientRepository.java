package com.advanced.java.FinalProject.repositories;

import com.advanced.java.FinalProject.entities.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    public Patient findById_(Long id);

}
