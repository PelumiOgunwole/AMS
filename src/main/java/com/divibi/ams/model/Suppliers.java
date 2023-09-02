package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "suppliers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private  Long supplier_id;
    @Column(name = "name")
    private  String Name;
    @Column(name = "contact_info")
    private  String contactInfo;

    // Mapping to component meaning  many suppliers to 1 component
    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;

}
