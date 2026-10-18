package com.project.booking_buddy.service;

import com.project.booking_buddy.entity.Booking;

public interface CheckoutService {
    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);
}
