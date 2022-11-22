package com.lionsbot.evaluation.dibyajyoti.repository;

import com.lionsbot.evaluation.dibyajyoti.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("select a from Order a where a.customerId = :customerId")
    public List<Order>  getOrdersByCustomerId(@Param("customerId") String customerId);
}
