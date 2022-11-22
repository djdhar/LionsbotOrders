package com.lionsbot.evaluation.dibyajyoti.controller;

import com.lionsbot.evaluation.dibyajyoti.entity.*;
import com.lionsbot.evaluation.dibyajyoti.service.CustomerService;
import com.lionsbot.evaluation.dibyajyoti.service.OrderService;
import com.lionsbot.evaluation.dibyajyoti.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceController {
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderService orderService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtTokenUtil;

    @GetMapping("/orders")
    public List<Order> findAllOrders(@RequestHeader("Authorization") String token) {
        return orderService.getOrders(token);
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers(@RequestHeader("Authorization") String token) {
        return customerService.getCustomers(token);
    }

    @GetMapping("/orders/{customer_id}")
    public List<Order> findOrdersByCustomerId(@PathVariable int customer_id, @RequestHeader("Authorization") String token) {
        return orderService.getOrdersByCustomerId(customer_id, token);
    }

    @PutMapping("/orders/{order_id}")
    public Order updateOrder(@PathVariable int order_id, @RequestBody Order order, @RequestHeader("Authorization") String token) {
        return orderService.modifyOrder(order_id, order, token);
    }

    @PutMapping("/changepassword/{customer_id}")
    public Customer updatePassword(@PathVariable int customer_id, @RequestBody ChangeCustomerRequest changeCustomerRequest, @RequestHeader("Authorization") String token) {
        return customerService.changePassword(customer_id, changeCustomerRequest, token);
    }

    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order, @RequestHeader("Authorization") String token) {
        return orderService.addOrder(order, token);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") String token) {
        return customerService.addCustomer(customer, token);
    }

    @DeleteMapping("/orders/{orderId}")
    public String deleteOrder(@PathVariable int orderId, @RequestHeader("Authorization") String token) {
        return orderService.deleteOrder(orderId, token);
    }


    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId , @RequestHeader("Authorization") String token) {
        return customerService.deleteCustomer(customerId, token);
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println(authenticationRequest.toString());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUserId() + getAdminSuffix(authenticationRequest.getIsAdmin()),
                            authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect userId or password", e);
        }


        final UserDetails userDetails = customerService
                .loadUserByUsername(authenticationRequest.getUserId() + getAdminSuffix(authenticationRequest.getIsAdmin()));

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    private String getAdminSuffix(String isAdmin) {
        if(isAdmin==null) return "";
        if(isAdmin.equals("true")) return "_ADMIN_";
        else return "";
    }

}
