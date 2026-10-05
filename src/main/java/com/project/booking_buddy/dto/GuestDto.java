package com.project.booking_buddy.dto;

import com.project.booking_buddy.entity.Booking;
import com.project.booking_buddy.entity.User;
import com.project.booking_buddy.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Booking> bookings;
}
