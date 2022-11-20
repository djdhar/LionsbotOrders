package com.lionsbot.evaluation.dibyajyoti.controller;

import com.lionsbot.evaluation.dibyajyoti.entity.Customer;
import com.lionsbot.evaluation.dibyajyoti.entity.Order;
import com.lionsbot.evaluation.dibyajyoti.service.CustomerService;
import com.lionsbot.evaluation.dibyajyoti.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public List<Order> findAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/orders/{customer_id}")
    public List<Order> findOrdersByCustomerId(@PathVariable int customer_id) {
        return orderService.getOrdersByCustomerId(customer_id);
    }

    @PutMapping("/orders/{order_id}")
    public Order updateOrder(@PathVariable int order_id, @RequestBody Order order) {
        return orderService.modifyOrder(order_id, order);
    }

    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @DeleteMapping("/orders/{orderId}")
    public String deleteOrder(@PathVariable int orderId) {
        return orderService.deleteOrder(orderId);
    }


    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        return customerService.deleteCustomer(customerId);
    }

}
