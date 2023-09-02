//package com.divibi.ams.model;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.Generated;
//import org.hibernate.annotations.GenerationTime;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.data.annotation.CreatedDate;
//
//import javax.persistence.*;
//import java.util.Date;
//@Entity
//@Table(name="maintenance_record")
//public class MaintenanceRecord {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "maintenance_id")
//    private int maintenanceId;
//    @Column(name = "component_id")
//    private int componentId;
//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "maintenance_date")
//    private Date maintenanceDate;
//    @UpdateTimestampa
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_maintenance_date")
//    private Date upDatedMaintenanceDate;
//    private String description;
//
//    public MaintenanceRecord(int maintenanceId, int componentId, Date maintenanceDate, String description,Date upDatedMaintenanceDate) {
//        this.maintenanceId = maintenanceId;
//        this.componentId = componentId;
//        this.maintenanceDate = maintenanceDate;
//        this.description = description;
//        this.upDatedMaintenanceDate=upDatedMaintenanceDate;
//    }
//
//    public MaintenanceRecord() {
//
//    }
//
//    public int getMaintenanceId() {
//        return maintenanceId;
//    }
//
//    public void setMaintenanceId(int maintenanceId) {
//        this.maintenanceId = maintenanceId;
//    }
//
//    public int getComponentId() {
//        return componentId;
//    }
//
//    public void setComponentId(int componentId) {
//        this.componentId = componentId;
//    }
//
//    public Date getMaintenanceDate() {
//        return maintenanceDate;
//    }
//
//    public void setMaintenanceDate(Date maintenanceDate) {
//        this.maintenanceDate = maintenanceDate;
//    }
//    public Date getUpDatedMaintenanceDate() {
//        return upDatedMaintenanceDate;
//    }
//
//    public void setUpDatedMaintenanceDate(Date maintenanceDate) {
//        this.upDatedMaintenanceDate = upDatedMaintenanceDate;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    // Add any other methods for the MaintenanceRecord class as needed
//}
