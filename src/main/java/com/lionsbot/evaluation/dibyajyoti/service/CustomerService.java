package com.lionsbot.evaluation.dibyajyoti.service;

import com.lionsbot.evaluation.dibyajyoti.entity.Admin;
import com.lionsbot.evaluation.dibyajyoti.entity.Customer;
import com.lionsbot.evaluation.dibyajyoti.entity.Order;
import com.lionsbot.evaluation.dibyajyoti.repository.AdminRepository;
import com.lionsbot.evaluation.dibyajyoti.repository.CustomerRepository;
import com.lionsbot.evaluation.dibyajyoti.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AdminRepository adminRepository;

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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(isAdmin(username)) {
            Optional<Admin> admin = adminRepository.findById(
                    Integer.parseInt(username.substring(0, username.length()-7))
            );
            if(!admin.isPresent()) throw new UsernameNotFoundException("admin Not found");
            System.out.println("Customer is found " + admin.get());
            return new User(username, admin.get().getPassword(), new ArrayList<>());
        }
        Optional<Customer> customer = customerRepository.findById(Integer.parseInt(username));
        if(!customer.isPresent()) throw new UsernameNotFoundException("customer Not found");
        System.out.println("Customer is found " + customer.get());
        return new User(String.valueOf(customer.get().getId()), customer.get().getPassword(),
                new ArrayList<>());
    }

    private boolean isAdmin(String username) {
        if(username.endsWith("_ADMIN_")) return  true;
        return false;
    }
}
