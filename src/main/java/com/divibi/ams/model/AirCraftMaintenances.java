package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirCraftMaintenances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id")
    private Long maintenanceId;
    @Column(name = "description")
    private String maintenanceDescription;
    @Column(name = "type")
    private String maintenanceType;
    @Column(name = "performed_by")
    private String performedBy;

    @Column(name = "scheduled_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date scheduledDate;

    @Column(name = "performed_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date performedDate;

    @Column(name = "status")
    private String status;

    @Column(name = "ATA_Chapter")
    private String ATAChapter;

    @Column(name = "fault_code")
    private String faultCode;

    @Column(name = "fault_description")
    private String faultDescription;

    @Column(name = "corrective_action")
    private String correctiveAction;


    @Column(name = "routine")
    private boolean routine;

    @Column(name = "priority")
    private String priority;

    @Column(name = "man_hours_required")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime manHoursRequired;

    @Column(name = "tools_required")
    private String toolsRequired;

    @Column(name = "parts_required")
    private String partsRequired;

    @Column(name = "cost_estimation")
    private double costEstimation;

    @Column(name = "deferred")
    private boolean deferred;

    @Column(name = "deferral_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deferralDate;

    @Column(name = "deferral_justification")
    private String deferalJustification;

    @Column(name = "compliance_certificates")
    private String complianceCertificates;

    @Column(name = "safety_measures")
    private String safetyMeasures;

    @Column(name = "environmental_impact")
    private String environmentalImpact;

    @Column(name = "risk_assessment")
    private String riskAssesment;

    @Column(name = "additional_notes")
    private String additionalNotes;
}
