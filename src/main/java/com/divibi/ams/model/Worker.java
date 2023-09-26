package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="worker")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Long workerId;
    @Column(name = "name")
    private String Name;
    @Column(name = "position")
    private String position;
    @Column(name = "contact_info")
    private String contactInfo;
    // Add other attributes for the worker as needed


}

