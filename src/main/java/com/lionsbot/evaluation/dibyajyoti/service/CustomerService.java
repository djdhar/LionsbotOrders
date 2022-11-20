package com.lionsbot.evaluation.dibyajyoti.service;

import com.lionsbot.evaluation.dibyajyoti.entity.Customer;
import com.lionsbot.evaluation.dibyajyoti.entity.Order;
import com.lionsbot.evaluation.dibyajyoti.repository.CustomerRepository;
import com.lionsbot.evaluation.dibyajyoti.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public String deleteCustomer(int id) {
        List<Order> orders = orderRepository.getOrdersByCustomerId(id);
        List<Integer> orderIds = orders.stream().map(Order::getId)
                .collect(Collectors.toList());
        orderRepository.deleteAllById(orderIds);
        customerRepository.deleteById(id);
        return "customer removed !! " + id;
    }

}
