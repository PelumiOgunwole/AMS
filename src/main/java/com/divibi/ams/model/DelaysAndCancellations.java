package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="DelaysAndCancellations")

public class DelaysAndCancellations {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "ac")
    private Long ac;

    @Column(name = "date")
    private Date date;
    @Column(name = "station")
    private String station;
    @Column(name = "Flight-No")
    private String flightNo;

    @Column(name = "type")
    private String type;
    @Column(name = "delay")
    private Time delay;
    @Column(name = "ir_code")
    private Double irCode;

    @Column(name = "event_id")
    private Long eventId;
    @Column(name = "delay_desc")
    private String delayDesc;

}
