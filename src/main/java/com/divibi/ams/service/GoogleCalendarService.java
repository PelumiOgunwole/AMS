package com.divibi.ams.service;
import com.divibi.ams.model.Aircraft;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public  class GoogleCalendarService {

    private  final AircraftService aircraftService;

    public GoogleCalendarService(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    // Create and return a list of Google Calendar events with Aircraft properties in descriptions
    public List<Event> getEventsWithAircraftProperties() {
        // Fetch Aircraft data (replace this with your data retrieval logic)
        List<Aircraft> aircraftList = aircraftService.getAllAircrafts();

        // Create a list to store Google Calendar events
        List<Event> googleCalendarEvents = new ArrayList<>();

        // Iterate over Aircraft data and create events with descriptions
        for (Aircraft aircraft : aircraftList) {
            Event event = new Event();
            event.setSummary("Aircraft Maintenance");
            event.setDescription("Aircraft Name: " + aircraft.getTailNumber() +
                    "\nNext Maintenance Date: " + aircraft.getNextMaintenanceDate() +
                    "\nLast Repair Date: " + aircraft.getLastRepairDate());

            // Set the start and end times for the event to the nextMaintenanceDate of the Aircraft
            LocalDateTime nextMaintenanceDateTime = aircraft.getNextMaintenanceDate().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();

            // Convert LocalDateTime to Date
            Date nextMaintenanceDate = Date.from(nextMaintenanceDateTime.atZone(ZoneId.systemDefault()).toInstant());

            // Create a DateTime object from the Date
            EventDateTime eventStartDateTime = new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(nextMaintenanceDate));
            event.setStart(eventStartDateTime);

            // Set the end time (adjust as needed)
            LocalDateTime endDateTime = nextMaintenanceDateTime.plusHours(2); // Event duration is 2 hours

            // Convert LocalDateTime to Date
            Date endDate = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());

            // Create a DateTime object from the Date
            EventDateTime eventEndDateTime = new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(endDate));
            event.setEnd(eventEndDateTime);

            // Add the event to the list
            googleCalendarEvents.add(event);
        }

        return googleCalendarEvents;
    }

}





