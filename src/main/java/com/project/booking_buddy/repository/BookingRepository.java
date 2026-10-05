package com.project.booking_buddy.repository;

import com.project.booking_buddy.entity.Booking;
import com.project.booking_buddy.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
