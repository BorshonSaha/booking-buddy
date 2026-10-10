package com.project.booking_buddy.strategy;

import com.project.booking_buddy.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
