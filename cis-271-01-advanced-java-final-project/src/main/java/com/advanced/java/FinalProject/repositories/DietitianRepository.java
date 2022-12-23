package com.advanced.java.FinalProject.repositories;

import com.advanced.java.FinalProject.entities.Dietitian;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietitianRepository  extends CrudRepository<Dietitian, Long> {
    Dietitian findBypatient_id(Long patient_id);
}
