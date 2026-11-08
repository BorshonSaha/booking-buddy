package com.project.booking_buddy.repository;

import com.project.booking_buddy.entity.Hotel;
import com.project.booking_buddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByOwner(User user);
}
