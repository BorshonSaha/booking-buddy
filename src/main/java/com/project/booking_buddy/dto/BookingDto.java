package com.project.booking_buddy.dto;

import com.project.booking_buddy.entity.Guest;
import com.project.booking_buddy.entity.Hotel;
import com.project.booking_buddy.entity.Room;
import com.project.booking_buddy.entity.User;
import com.project.booking_buddy.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
}
