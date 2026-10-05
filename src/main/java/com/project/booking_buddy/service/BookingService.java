package com.project.booking_buddy.service;

import com.project.booking_buddy.dto.BookingDto;
import com.project.booking_buddy.dto.BookingRequest;
import com.project.booking_buddy.dto.GuestDto;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface BookingService {
    @Nullable BookingDto initializeBooking(BookingRequest bookingRequest);

    @Nullable BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
