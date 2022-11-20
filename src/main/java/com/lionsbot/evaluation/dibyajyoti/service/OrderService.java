package com.lionsbot.evaluation.dibyajyoti.service;

import com.lionsbot.evaluation.dibyajyoti.entity.Customer;
import com.lionsbot.evaluation.dibyajyoti.entity.Order;
import com.lionsbot.evaluation.dibyajyoti.repository.CustomerRepository;
import com.lionsbot.evaluation.dibyajyoti.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerId(Integer customerId) {
        return orderRepository.getOrdersByCustomerId(customerId);
    }

    public Order addOrder(Order order) {
        order.setOrderDate(new Date());
        if(customerRepository.findById(order.getCustomerId()).isPresent()) {
            return orderRepository.save(order);
        }
        return null;
    }

    public Order modifyOrder(int orderId, Order order) {
        Optional<Order> dbOrder = orderRepository.findById(orderId);
        Optional<Customer> dbCustomer = customerRepository.findById(order.getCustomerId());
        if(!dbCustomer.isPresent()) return null;
        if(!dbOrder.isPresent()) return null;
        Order actualOrder = dbOrder.get();
        actualOrder.setTotalPrice(order.getTotalPrice());
        actualOrder.setNumberOfItems(order.getNumberOfItems());
        actualOrder.setOrderDate(order.getOrderDate());
        actualOrder.setCustomerId(order.getCustomerId());
        return orderRepository.save(actualOrder);
    }

    public String deleteOrder(int id) {
        orderRepository.deleteById(id);
        return "order removed !! " + id;
    }
}
