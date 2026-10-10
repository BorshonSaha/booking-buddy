package com.project.booking_buddy.strategy;

import com.project.booking_buddy.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class OccupancyPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);

        Integer bookedCount = inventory.getBookedCount();
        Integer totalCount = inventory.getTotalCount();

        double occupancyRate = (double) bookedCount / totalCount;

        if(occupancyRate > 0.8) {
            price = price.multiply(BigDecimal.valueOf(1.2));
        }
        return price;
    }
}
