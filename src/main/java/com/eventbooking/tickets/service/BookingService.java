package com.eventbooking.tickets.service;

import com.eventbooking.tickets.dto.BookingCreateDto;
import com.eventbooking.tickets.entity.Booking;
import com.eventbooking.tickets.entity.Event;
import com.eventbooking.tickets.repository.BookingRepository;
import org.springframework.stereotype.Service;
import com.eventbooking.tickets.repository.EventRepository;
@Service
public class BookingService {
    private final EventRepository eventRepository;
    private final BookingRepository bookingRepository;

    public BookingService(EventRepository eventRepository, BookingRepository bookingRepository) {
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
    }

    public String createBooking(BookingCreateDto bookingCreateDto) {

        Event event = eventRepository
                .findById(bookingCreateDto.getEventId())
                .orElse(null);

        if (event == null) {
            return "Event not found";
        }

        double totalAmount =
                event.getTicketPrice() * bookingCreateDto.getNumberOfTickets();

        Booking booking = new Booking();

        booking.setEventId(event.getId());
        booking.setNumberOfTickets(bookingCreateDto.getNumberOfTickets());
        booking.setTotalAmount(totalAmount);
        booking.setBookingDate("2026-09-04");
        booking.setBookingStatus("CONFIRMED");

        bookingRepository.save(booking);

        return "Booking successful";
    }
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
    public String cancelBooking(Long id) {

        Booking booking = bookingRepository.findById(id).orElse(null);

        if (booking == null) {
            return "Booking not found";
        }

        bookingRepository.deleteById(id);

        return "Booking cancelled successfully";
    }

}

