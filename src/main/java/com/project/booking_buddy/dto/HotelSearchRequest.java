package com.project.booking_buddy.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelSearchRequest {

    private String city;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer roomsCount = 1;
    private Integer page = 0;
    private Integer size = 10;
}
