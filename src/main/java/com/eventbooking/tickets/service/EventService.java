package com.eventbooking.tickets.service;

import com.eventbooking.tickets.dto.EventCreatedto;
import com.eventbooking.tickets.entity.Event;
import com.eventbooking.tickets.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    //create the event
    public Event createEvent(EventCreatedto eventCreateDTO) {

        Event event = new Event();

        event.setEventName(eventCreateDTO.getEventName());
        event.setTicketPrice(eventCreateDTO.getTicketPrice());
        event.setContactNumber(eventCreateDTO.getContactNumber());
        event.setOrganizerName(eventCreateDTO.getOrganizerName());
        event.setEventDate(eventCreateDTO.getEventDate());


        return eventRepository.save(event);
    }
    //get the event details
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
    //GET BY EVENTID
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
    //edit by id
    public String updateEvent(Long id, EventCreatedto eventCreatedto) {

        Event event = eventRepository.findById(id).orElse(null);

        if (event == null) {
            return "Event not found";
        }

        event.setEventName(eventCreatedto.getEventName());
        event.setTicketPrice(eventCreatedto.getTicketPrice());
        event.setContactNumber(eventCreatedto.getContactNumber());
        event.setOrganizerName(eventCreatedto.getOrganizerName());
        event.setEventDate(eventCreatedto.getEventDate());
        eventRepository.save(event);

        return "Event updated successfully";
    }
    //delete by id
    public String deleteEvent(Long id) {

        Event event = eventRepository.findById(id).orElse(null);

        if (event == null) {
            return "Event not found";
        }

        eventRepository.deleteById(id);

        return "Event deleted successfully";
    }
}