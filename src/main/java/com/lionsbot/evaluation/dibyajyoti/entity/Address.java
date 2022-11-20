package com.lionsbot.evaluation.dibyajyoti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ADDRESS_TBL")

public class Address {

    @Id
    @GeneratedValue
    private int id;

    @NonNull
    @Column(name = "customer_id")
    private int customerId;

    @NonNull
    @Column(length = 150)
    private String address1;

    @Column(length = 150)
    private String address2;

    @NonNull
    private Integer postalCode;

    @NonNull
    @Column(length = 50)
    private String country;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;
}
