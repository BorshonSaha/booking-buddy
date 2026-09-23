package com.project.booking_buddy.service;


import com.project.booking_buddy.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto createNewRoom(Long hotelId, RoomDto roomDto);

    RoomDto getRoomById(Long id);

    List<RoomDto> getAllRoomsInHotel(Long hotelId);

    void deleteRoomById(Long roomId);

    RoomDto updateRoomById(Long hotelId, Long roomId, RoomDto roomDto);
}
