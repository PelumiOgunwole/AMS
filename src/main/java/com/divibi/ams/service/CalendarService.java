package com.divibi.ams.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CalendarService {

    private static final String APPLICATION_NAME = "AMS";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "src/main/resources/";

    // Initialize Google Calendar API
    private Calendar getCalendarService() throws Exception {
        try {
            InputStream in = GoogleCalendarService.class.getResourceAsStream("/gcred.json");
            GoogleCredentials credentials = ServiceAccountCredentials.fromStream(in)
                    .createScoped(Collections.singletonList(CalendarScopes.CALENDAR));

            // Adapt GoogleCredentials to HttpRequestInitializer
            HttpRequestInitializer requestInitializer = request -> {
                HttpRequestInitializer httpRequestInitializer =
                        new HttpCredentialsAdapter(credentials);
                httpRequestInitializer.initialize(request);
            };

            return new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, requestInitializer)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        } catch (Exception e) {
            // Handle or log the exception
            e.printStackTrace();
            throw e; // Rethrow the exception or handle it as needed
        }
    }



    // Add Event to Google Calendar
    public String addEvent(Date start, Date end, String summary) throws Exception {
        Event event = new Event().setSummary(summary);

        EventDateTime startDateTime = new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(start));
        EventDateTime endDateTime = new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(end));

        event.setStart(startDateTime);
        event.setEnd(endDateTime);

        Calendar service = getCalendarService();
        String calendarId = "primary";
        event = service.events().insert(calendarId, event).execute();

        return event.getId();
    }

    // Get Events from Google Calendar
    public List<Event> getEvents() throws Exception {
        Calendar service = getCalendarService();
        String calendarId = "primary";
        return service.events().list(calendarId).execute().getItems();
    }
}

