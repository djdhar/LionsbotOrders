package com.lionsbot.evaluation.dibyajyoti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER_TBL")

public class Customer {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    @NonNull
    @Column(length = 50)
    private String customerName;

    @NonNull
    @Column(length = 100)
    private String email;

    @NonNull
    private String password;

    @NonNull
    @Column(length = 15)
    private String contactNumber;
}
