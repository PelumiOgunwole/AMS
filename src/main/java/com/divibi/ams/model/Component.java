package com.divibi.ams.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
@Table(name="component")
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private int componentId;
    @Column(name = "component_name")
    private String componentName;
    private String manufacturer;
    @Column(name = "flight_hours")
    private int flightHours;

    @Column(name = "status")
    private String status;

    public Component(int componentId, String componentName, String manufacturer, int flightHours, String status) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.manufacturer = manufacturer;
        this.flightHours = flightHours;
        this.status = status;
    }

    public Component() {

    }

    // Add getters and setters for the attributes
    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Add any other methods for the Component class as needed
//    private int maintenanceRecordCount; // Поле для хранения количества записей об обслуживании

    // Метод для получения количества записей об обслуживании
//    public int getMaintenanceRecordCount() {
//        return maintenanceRecordCount;
//    }

    // Метод для установки количества записей об обслуживании
//    public void setMaintenanceRecordCount(int maintenanceRecordCount) {
//        this.maintenanceRecordCount = maintenanceRecordCount;
//    }
}
