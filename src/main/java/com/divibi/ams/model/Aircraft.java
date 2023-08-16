package com.divibi.ams.model;

import javax.persistence.*;

@Entity
@Table(name="aircraft")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id")
    private int aircraftId;
    @Column(name = "aircraft_code")
    private String aircraftCode;
    @Column(name = "aircraft_model")
    private String aircraftModel;
    @Column(name = "total_flight_hours")
    private int totalFlightHours;
    // Add other aircraft attributes as needed

    public Aircraft(int aircraftId, String aircraftCode, String aircraftModel, int totalFlightHours) {
        this.aircraftId = aircraftId;
        this.aircraftCode = aircraftCode;
        this.aircraftModel = aircraftModel;
        this.totalFlightHours = totalFlightHours;
        // Initialize other aircraft attributes as needed
    }

    public Aircraft() {

    }

    // Add getters and setters for all attributes

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(String aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public int getTotalFlightHours() {
        return totalFlightHours;
    }

    public void setTotalFlightHours(int totalFlightHours) {
        this.totalFlightHours = totalFlightHours;
    }

    // Add other setters and getters for other aircraft attributes as needed

    // Add other methods as needed
}
