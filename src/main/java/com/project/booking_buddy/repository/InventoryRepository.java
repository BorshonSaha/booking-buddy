package com.project.booking_buddy.repository;

import com.project.booking_buddy.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
