package com.project.booking_buddy.repository;

import com.project.booking_buddy.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}