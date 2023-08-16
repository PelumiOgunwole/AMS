package com.divibi.ams.model;

import javax.persistence.*;

@Entity
@Table(name="serial_number")
public class SerialNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_number_id")
    private int serialNumberId;
    @Column(name = "component_id")
    private int componentId;
    @Column(name = "serial_number_value")
    private String serialNumberValue;

    public SerialNumber(int serialNumberId, int componentId, String serialNumberValue) {
        this.serialNumberId = serialNumberId;
        this.componentId = componentId;
        this.serialNumberValue = serialNumberValue;
    }

    public SerialNumber() {

    }

    public int getSerialNumberId() {
        return serialNumberId;
    }

    public void setSerialNumberId(int serialNumberId) {
        this.serialNumberId = serialNumberId;
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public String getSerialNumberValue() {
        return serialNumberValue;
    }

    public void setSerialNumberValue(String serialNumberValue) {
        this.serialNumberValue = serialNumberValue;
    }

    // Add any other methods for the SerialNumber class as needed
}
