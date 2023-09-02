package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="component")
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long componentId;

    @Column(name = "ata_number")
    private String ATANumber;

    // Mapping to suppliers meaning  1 components to many suppliers
    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Suppliers> suppliers = new ArrayList<Suppliers>();

    // Mapping to aircraft meaning  many components to 1 aircraft
    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @Column(name = "name")
    private String componentName;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "description")
    private String description;

    @Column(name = "installation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date installationDate;

    @Column(name = "warranty_expiration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date warrantyExpirationDate;

    @Column(name = "unscheduled_removal")
    private Long unscheduledRemoval;

    @Column(name = "MTBF")
    private double MTBF;

    @Column(name = "MTBUR")
    private double MTBUR;

    @Column(name = "MTTF")
    private double MTTF;

    @Column(name = "LIFESPAN")
    private double lifeSpan;

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "critical_component")
    private boolean critical_component;

    @Column(name = "maintenance_cycle")
    private double maintenanceCycle;

    @Column(name = "last_maintenance_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastMaintenanceDate;

    @Column(name = "next_maintenance_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextMaintenanceDate;

    @Column(name = "repair_history")
    private String repairHistory;

    @Column(name = "overhaul_history")
    private String overhaulHistory;

    @Column(name = "cost")
    private double cost;

    @Column(name = "warranty_terms")
    private String warrantyTerms;

    @Column(name = "location")
    private String location;

    @Column(name = "compliance_certificates")
    private String complianceCertificates;

    @Column(name = "work_order_list")
    private String workOrderList;

    @Column(name = "damage_numbers")
    private String damageNumbers;

    @Column(name = "operating_hours_first_failure")
    private double operatingHoursFirstFailure;

    @Column(name = "operating_hours_second_failure")
    private double operatingHoursSecondFailure;

    @Column(name = "operating_hours_third_failure")
    private double operatingHoursThirdFailure;

    @Column(name = "failure_replacement_frequency")
    private String failureReplacementFrequency;

    @Column(name = "scheduled_maintenance_replacement_frequency")
    private String scheduledMmaintenanceReplacementFrequency;

    @Column(name = "number_removals")
    private String numberRemovals;

    @Column(name = "status")
    private String status;



}
