package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="staff_and_pq5_configuration")
public class StaffAndPQ5Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_config_id")
    private Long staffConfigId;
    @Column(name = "qualifications")
    @NotNull
    private String qualifications;
    @Column(name = "label")
    @NotNull
    private String label;
    @Column(name = "description")
    @NotNull
    private String description;
    @Column(name = "sys")
//    @NotNull
    private String system;
    @Column(name = "planning")
    @NotNull
    private String planning;
    @Column(name = "signing")
    @NotNull
    private String signing;
    @Column(name = "training")
    @NotNull
    private String training;
}
