package com.eventbooking.tickets.repository;

import com.eventbooking.tickets.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}