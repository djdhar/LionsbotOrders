package com.lionsbot.evaluation.dibyajyoti.repository;

import com.lionsbot.evaluation.dibyajyoti.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}
