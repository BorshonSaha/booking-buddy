package com.project.booking_buddy.repository;

import com.project.booking_buddy.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
