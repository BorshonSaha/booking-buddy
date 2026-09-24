package com.project.booking_buddy.service;

import com.project.booking_buddy.dto.HotelDto;
import com.project.booking_buddy.dto.HotelSearchRequest;
import com.project.booking_buddy.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
