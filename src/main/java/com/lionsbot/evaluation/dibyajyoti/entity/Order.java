package com.lionsbot.evaluation.dibyajyoti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_TBL")

public class Order {

    @Id
    @GeneratedValue
    private int id;

    @NonNull
    @Column(name = "customer_id")
    private int customerId;

    @NonNull
    private Date orderDate;

    @NonNull
    private Double totalPrice;

    @NonNull
    private Integer numberOfItems;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;
}
