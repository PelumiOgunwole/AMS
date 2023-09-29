package com.divibi.ams.model;

//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="aircraft")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id")
    private Long aircraftId;
    @Column(name = "flight_hours")
    @NotNull
    @Min(value = 1, message = "Value must be greater than or equal to 1")
    private double totalFlightHours;
    @Column(name = "tail_number")
    @NotEmpty(message = "Field must not be empty")
    private String tailNumber;
    @Column(name = "serial_number")
    @NotEmpty(message = "Field must not be empty")
    private String serialNumber;
    @Column(name = "manufacturer")
    @NotEmpty(message = "Field must not be empty")
    private String manufacturer;
    @Column(name = "type")
    @NotEmpty(message = "Field must not be empty")
    private String type;
    @Column(name = "capacity")
    @Min(value = 1, message = "Value must be greater than or equal to 1")
    @NotNull(message = "Field must not be empty")
    private int capacity;
    @Column(name = "base_location")
    @NotEmpty(message = "Field must not be empty")
    private String baseLocation;
    @Column(name = "current_location")
    @NotEmpty(message = "Field must not be empty")
    private String currentLocation;
    @Column(name = "technical_status")
    @NotEmpty(message = "Field must not be empty")
    private String technicalStatus;

    @Column(name = "last_repair_date")
    @Temporal(value = TemporalType.DATE)
//    @NotNull
    private Date lastRepairDate;

    @Column(name = "next_maintenance_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @NotNull
    private Date nextMaintenanceDate;

    @Column(name = "deferred_maintenance_tasks")
    @NotNull(message = "Field must not be empty")
    @Min(value = 0, message = "Value must be greater than or equal to 0")
    private Long deferredMaintenanceTasks;
    @BatchSize(size = 10)
    @OneToMany( fetch = FetchType.LAZY, cascade= CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "aircraft_worker_id")
    private Set<Worker> workers = new HashSet<Worker>() ;

    @BatchSize(size = 10)
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_component_id")
    private Set<Component>  components = new HashSet<Component>();

    @BatchSize(size = 10)
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_maintenance_id")
    private Set<AirCraftMaintenances>  aircraftMaintenances = new HashSet<AirCraftMaintenances>();

}
