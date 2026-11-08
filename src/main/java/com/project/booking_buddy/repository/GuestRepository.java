package com.project.booking_buddy.repository;

import com.project.booking_buddy.entity.Guest;
import com.project.booking_buddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByUser(User user);
}