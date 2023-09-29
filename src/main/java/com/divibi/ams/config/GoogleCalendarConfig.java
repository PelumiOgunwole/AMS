//package com.divibi.ams.config;
//
//import com.google.api.services.calendar.CalendarScopes;
//import com.google.auth.oauth2.GoogleCredentials;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Collections;
//
//@Configuration
//public class GoogleCalendarConfig {
//    @Value("${jsonFilePath}")
//    private String jsonFilePath;
//    @Bean
//    public GoogleCredentials googleCredentials() throws IOException {
//        File file = new File(jsonFilePath);
//
//        // Check if the file exists and is readable
//        if (!file.exists() || !file.canRead()) {
//            throw new IOException("Unable to access the JSON file.");
//        }
//
//        // Load the JSON file from the file system
//        FileInputStream in = new FileInputStream(file);
//
//        // Create GoogleCredentials from the JSON content
//        GoogleCredentials credentials = GoogleCredentials.fromStream(in);
//
//        return credentials;
//    }
//}
//
