package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="parts_reliability")

public class SystemReliability {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "description")
    private String description;

    @Column(name = "pireps")
    private Long pireps;
    @Column(name = "ATA")
    private String ATA;
    @Column(name = "maint_def")
    private double maintenanceDef;

    @Column(name = "cabin_def")
    private double cabinDef;
    @Column(name = "sched_wo")
    private Long schedWO;
    @Column(name = "total_wo")
    private double totalWo;

    @Column(name = "pir_rate")
    private double pirepsRate;
    @Column(name = "pir_rate_ucl")
    private double pirepsRateUCL;
    @Column(name = "pir_rate_twelve_months")
    private double pirepsRateTwelveMonths;

    @Column(name = "maint_def_rate")
    private double maintDefRate;
    @Column(name = "maint_def_rate_ucl")
    private double maintDefRateUCL;

    @Column(name = "maint_def_rate_twelve_months")
    private double maintDefRateTwelveMonths;

    @Column(name = "cabin_def_rate")
    private double cabinDefRate;
    @Column(name = "cabin_def_rate_ucl")
    private double cabinDefRateUCL;
    @Column(name = "cabin_def_rate_twelve_months")
    private double cabinDefRateTwelveMonths;
    @Column(name = "total_us_wo_rate")
    private double totalUsWoRate;
    @Column(name = "sched_wo_rate")
    private double schedWoRate;

}