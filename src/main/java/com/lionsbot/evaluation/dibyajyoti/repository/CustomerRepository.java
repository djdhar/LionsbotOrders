package com.lionsbot.evaluation.dibyajyoti.repository;

import com.lionsbot.evaluation.dibyajyoti.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
