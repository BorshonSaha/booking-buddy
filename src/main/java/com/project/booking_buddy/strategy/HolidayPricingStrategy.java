package com.project.booking_buddy.strategy;

import com.project.booking_buddy.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy {

    private static final BigDecimal HOLIDAY_MULTIPLIER = BigDecimal.valueOf(1.25);

    // hard code holiday list, need to call API to check holiday
    private static final List<LocalDate> HOLIDAYS = Arrays.asList(
            LocalDate.of(2026, 12, 25),  // Christmas Day
            LocalDate.of(2027, 1, 1)   // New Year's Day
    );

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);

        // Checking if the inventory date exists in our internal holiday list
        if (inventory.getDate() != null && HOLIDAYS.contains(inventory.getDate())) {
            price = price.multiply(HOLIDAY_MULTIPLIER);
        }

        return price;
    }
}