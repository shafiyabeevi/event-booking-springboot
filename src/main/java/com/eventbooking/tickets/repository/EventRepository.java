package com.eventbooking.tickets.repository;

import com.eventbooking.tickets.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}