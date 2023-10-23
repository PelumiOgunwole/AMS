package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Timer;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="parts_reliability")
public class PartsReliability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "description")
    private String description;

    @Column(name = "p_n")
    private String pn;
    @Column(name = "ATA")
    private String ATA;
    @Column(name = "ac_group")
    private String acGroup;

    @Column(name = "unit_hours")
    private LocalTime unitHours;
    @Column(name = "us_rem")
    private Long usRem;
    @Column(name = "rate_us_rem")
    private double rateUsRem;

    @Column(name = "failures")
    private Long failures;
    @Column(name = "rate_failures")
    private double rateFailures;
    @Column(name = "no_fault")
    private Long noFault;

    @Column(name = "MTBF")
    private double MTBF;

    @Column(name = "MTBUR")
    private double MTBUR;

    @Column(name = "MTTUR")
    private double MTTUR;
    @Column(name = "nff_rate")
    private double nffRate;

}
