package com.project.booking_buddy.repository;

import com.project.booking_buddy.entity.Inventory;
import com.project.booking_buddy.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
