package com.lionsbot.evaluation.dibyajyoti.repository;

import com.lionsbot.evaluation.dibyajyoti.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
