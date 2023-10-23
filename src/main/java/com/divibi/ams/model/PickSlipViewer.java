package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pick_slip_viewer")

public class PickSlipViewer {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "pick_slip_no")
    private Long pickSlipNo;

    @Column(name = "station_from")
    private String stationFrom;
    @Column(name = "store_from")
    private String storeFrom;
    @Column(name = "station_to")
    private String stationTo;

    @Column(name = "store_to")
    private String storeTo;
    @Column(name = "mech")
    private String mech;
    @Column(name = "receiver")
    private String receiver;

    @Column(name = "project")
    private String project;
    @Column(name = "wo")
    private Long wo;
    @Column(name = "planned")
    private Date planned;

    @Column(name = "issued")
    private Date issued;
    @Column(name = "time_issued")
    private LocalTime timeIssued;

    @Column(name = "booked")
    private Date booked;

    @Column(name = "time_booked")
    private LocalTime timeBooked;
    @Column(name = "status")
    private String status;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "print")
    private String print;
    @Column(name = "availability")
    private String availability;

}
