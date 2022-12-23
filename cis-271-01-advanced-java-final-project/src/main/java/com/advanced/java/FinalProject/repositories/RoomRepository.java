package com.advanced.java.FinalProject.repositories;

import com.advanced.java.FinalProject.entities.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
