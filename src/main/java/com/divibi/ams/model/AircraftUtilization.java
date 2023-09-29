package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="aircraft_utilization")
public class AircraftUtilization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "dep_date")
    @NotEmpty(message = "Field must not be empty")
    private Date depDate;
    @Column(name = "arrival_date")
    @NotEmpty(message = "Field must not be empty")
    private Date arrivalDate;
    @Column(name = "AC")
    @NotEmpty(message = "Field must not be empty")
    private String AC;
    @Column(name = "ac_type")
    @NotEmpty(message = "Field must not be empty")
    private String acType;
    @Column(name = "operation")
    @NotEmpty(message = "Field must not be empty")
    private String operation;
    @Column(name = "flight")
    @NotEmpty(message = "Field must not be empty")
    private String flight;
    @Column(name = "serv_type")
    @NotEmpty(message = "Field must not be empty")
    private String servType;
    @Column(name = "flight_log")
//    @NotEmpty(message = "Field must not be empty")
    private String flightLog;
    @Column(name = "per_day")
    @NotEmpty(message = "Field must not be empty")
    private int perDay;
    @Column(name = "hours")
    @NotEmpty(message = "Field must not be empty")
    private LocalTime hours;

    @Column(name = "block_hours")
//    @NotEmpty(message = "Field must not be empty")
    private LocalTime blockHours;
    @Column(name = "cycles")
    @NotEmpty(message = "Field must not be empty")
    private int cycles;
    @Column(name = "tah")
    @NotEmpty(message = "Field must not be empty")
    private LocalTime tah;
    @Column(name = "tac")
    @NotEmpty(message = "Field must not be empty")
    private double tac;
    @Column(name = "dep")
    @NotEmpty(message = "Field must not be empty")
    private String dep;
    @Column(name = "arr")
    private String arr;
    @Column(name = "departure_time")
    private LocalTime departureTime;
    @Column(name = "arrival_time")
    private LocalTime arrivalTime;
}
