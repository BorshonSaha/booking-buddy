package com.project.booking_buddy.service;

import com.project.booking_buddy.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);
}
