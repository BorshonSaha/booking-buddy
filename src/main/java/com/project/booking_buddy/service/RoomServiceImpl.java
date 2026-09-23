package com.project.booking_buddy.service;

import com.project.booking_buddy.dto.HotelDto;
import com.project.booking_buddy.dto.RoomDto;
import com.project.booking_buddy.entity.Hotel;
import com.project.booking_buddy.entity.Room;
import com.project.booking_buddy.exception.ResourceNotFoundException;
import com.project.booking_buddy.repository.HotelRepository;
import com.project.booking_buddy.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new room in hotel with id {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " +hotelId));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);
        log.info("Created a new room with ID {}", roomDto.getId());

//      create inventory as soon as room is created and if hotel is active
        if(hotel.isActive()) {
            inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in hotel with id {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " +hotelId));
        return hotel.getRooms()
                .stream()
                .map((room) -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long id) {
        log.info("Getting the room with ID: {}", id);
        Room room = roomRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " +id));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the room with ID: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+roomId));

//      delete all future inventories for this room
        inventoryService.deleteFutureInventories(room);

        roomRepository.deleteById(roomId);
    }

    @Override
    public RoomDto updateRoomById(Long hotelId, Long roomId, RoomDto roomDto) {
        log.info("Updating the room with ID: {}", roomId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " +hotelId));

        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: "+roomId));

        modelMapper.map(roomDto, room);
        room.setId(roomId);
        room = roomRepository.save(room);
        return modelMapper.map(room, RoomDto.class);
    }
}
