package com.project.booking_buddy.service;

import com.project.booking_buddy.entity.Booking;
import com.project.booking_buddy.entity.User;
import com.project.booking_buddy.repository.BookingRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutServiceImpl implements CheckoutService{

    private final BookingRepository bookingRepository;

    @Override
    public String getCheckoutSession(Booking booking, String successUrl, String failureUrl) {
        log.info("Creating session for booking with ID: {}", booking.getId());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
        Customer customer = Customer.create(
                    CustomerCreateParams.builder()
                            .setName(user.getName())
                            .setEmail(user.getEmail())
                            .build()
            );

            SessionCreateParams sessionCreateParams = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setCustomer(customer.getId())
                    .setSuccessUrl(successUrl)
                    .setCancelUrl(failureUrl)
                    .setBillingAddressCollection(SessionCreateParams.BillingAddressCollection.REQUIRED)
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("eur")
                                                .setUnitAmount(booking.getAmount().multiply(BigDecimal.valueOf(100)).longValue())
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(booking.getHotel().getName() +" : "+ booking.getRoom().getRoomType())
                                                                .setDescription("Booking ID: "+booking.getId())
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
            )
                    .build();

            Session session = Session.create(sessionCreateParams);

            booking.setPaymentSessionId(session.getId());
            bookingRepository.save(booking);

            return session.getUrl();

        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
