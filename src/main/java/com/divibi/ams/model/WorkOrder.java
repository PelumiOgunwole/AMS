package com.divibi.ams.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="work_order")
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_order_id")
    public Long workOrderId;

    @Column(name = "AC")
    @NotNull
    @Min(value = 1, message = "Value must be greater than or equal to 1")
    private String AC;

    @Column(name = "issue_date")
    @NotNull
    private Date IssueDate;

    @Column(name = "due_date")
    @NotNull
    private Date dueDate;

    @Column(name = "flags")
    @NotNull
    private double flags;



}
