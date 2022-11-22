package com.lionsbot.evaluation.dibyajyoti.service;

import com.lionsbot.evaluation.dibyajyoti.entity.Admin;
import com.lionsbot.evaluation.dibyajyoti.entity.ChangeCustomerRequest;
import com.lionsbot.evaluation.dibyajyoti.entity.Customer;
import com.lionsbot.evaluation.dibyajyoti.entity.Order;
import com.lionsbot.evaluation.dibyajyoti.repository.AdminRepository;
import com.lionsbot.evaluation.dibyajyoti.repository.CustomerRepository;
import com.lionsbot.evaluation.dibyajyoti.repository.OrderRepository;
import com.lionsbot.evaluation.dibyajyoti.utils.JwtUtil;
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

    @Autowired
    JwtUtil jwtUtil;

    public List<Customer> getCustomers(String token) {
        if(jwtUtil.isAdminToken(token))
            return customerRepository.findAll();
        return null;
    }

    public Customer addCustomer(Customer customer, String token) {
        if(jwtUtil.isAdminToken(token))
            return customerRepository.save(customer);
        return null;
    }

    public String deleteCustomer(String id, String token) {
        if(!jwtUtil.isAdminToken(token)) return "Admin can delete only";
        List<Order> orders = orderRepository.getOrdersByCustomerId(id);
        List<String> orderIds = orders.stream().map(Order::getId)
                .collect(Collectors.toList());
        orderRepository.deleteAllById(orderIds);
        customerRepository.deleteById(id);
        return "customer removed !! " + id;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(isAdmin(username)) {
            Optional<Admin> admin = adminRepository.findById(
                    (username.substring(0, username.length()-7))
            );
            if(!admin.isPresent()) throw new UsernameNotFoundException("admin Not found");
            System.out.println("Customer is found " + admin.get());
            return new User(username, admin.get().getPassword(), new ArrayList<>());
        }
        Optional<Customer> customer = customerRepository.findById((username));
        if(!customer.isPresent()) throw new UsernameNotFoundException("customer Not found");
        System.out.println("Customer is found " + customer.get());
        return new User(String.valueOf(customer.get().getId()), customer.get().getPassword(),
                new ArrayList<>());
    }

    public Customer changePassword(String customerId, ChangeCustomerRequest changeCustomerRequest, String bearerToken) {
        if(jwtUtil.extractUsername(bearerToken.substring(7)).equals(String.valueOf(customerId))) {
            Optional<Customer> customer = customerRepository.findById(customerId);
            if(customer.isPresent()) {
                Customer dbCustomer = customer.get();
                dbCustomer.setPassword(changeCustomerRequest.getPassword());
                return customerRepository.save(dbCustomer);
            }
        }
        return null;
    }

    private boolean isAdmin(String username) {
        if(username.endsWith("_ADMIN_")) return  true;
        return false;
    }
}
