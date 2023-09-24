package com.divibi.ams.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.sql.Time;
import java.util.Date;

public class AircraftUtilization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "dep_date")
    @NotEmpty(message = "Field must not be empty")
    private String depDate;
    @Column(name = "arrival_date")
//    @NotEmpty(message = "Field must not be empty")
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
    private Time hours;

    @Column(name = "block_hours")
//    @NotEmpty(message = "Field must not be empty")
    private Time blockHours;
    @Column(name = "cycles")
    @NotEmpty(message = "Field must not be empty")
    private int cycles;
    @Column(name = "tah")
    @NotEmpty(message = "Field must not be empty")
    private String tah;
    @Column(name = "tac")
    @NotEmpty(message = "Field must not be empty")
    private String tac;
    @Column(name = "dep")
    @NotEmpty(message = "Field must not be empty")
    private String dep;
    @Column(name = "arr")
    private String arr;
    @Column(name = "departure_time")
    private Date departureTime;
    @Column(name = "arrival_time")
    private Date arrivalTime;
}
