package com.project.booking_buddy.repository;

import com.project.booking_buddy.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
