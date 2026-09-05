package com.eventbooking.tickets.controller;

import com.eventbooking.tickets.dto.BookingCreateDto;
import com.eventbooking.tickets.entity.Booking;
import com.eventbooking.tickets.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public String createBooking(@RequestBody BookingCreateDto bookingCreateDto) {
        return bookingService.createBooking(bookingCreateDto);
    }
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }
    @DeleteMapping("/{id}")
    public String cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}