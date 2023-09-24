package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="parts_tracking")
public class PartsTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parts_tracking_id")
    private Long partsTrackingId;
    @Column(name = "part_number")
    @NotEmpty(message = "Field must not be empty")
    private String partNo;
    @Column(name = "serial_number")
    @NotEmpty(message = "Field must not be empty")
    private String serialNumber;
    @Column(name = "AC")
    @NotEmpty(message = "Field must not be empty")
    private String AC;
    @Column(name = "co")
    @NotEmpty(message = "Field must not be empty")
    private String Co;
    @Column(name = "order_number")
    @NotEmpty(message = "Field must not be empty")
    private String orderNumber;
    @Column(name = "loc_or_pos")
    @NotEmpty(message = "Field must not be empty")
    private String locationOrPosition;
    @Column(name = "label_number")
    @NotEmpty(message = "Field must not be empty")
    private String labelNumber;
    @Column(name = "instDate")
//    @NotEmpty(message = "Field must not be empty")
    private Date instDate;
    @Column(name = "tsn")
    @NotEmpty(message = "Field must not be empty")
    private String TSN;
    @Column(name = "csn")
    @NotEmpty(message = "Field must not be empty")
    private String CSN;
    @Column(name = "tso")
    @NotEmpty(message = "Field must not be empty")
    private String TSO;
    @Column(name = "cso")
    @NotEmpty(message = "Field must not be empty")
    private String CSO;
    @Column(name = "tsi")
    @NotEmpty(message = "Field must not be empty")
    private String TSI;
    @Column(name = "csi")
    @NotEmpty(message = "Field must not be empty")
    private String CSI;
    @Column(name = "to_go")
    private String toGo;
    @Column(name = "tail_number")
    private Date ExpiryDate;
}
