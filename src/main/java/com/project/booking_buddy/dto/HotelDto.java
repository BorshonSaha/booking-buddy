package com.project.booking_buddy.dto;

import com.project.booking_buddy.entity.HotelContactInfo;
import com.project.booking_buddy.entity.Room;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private boolean active;
    private HotelContactInfo contactInfo;
    private List<Room> rooms;
}
