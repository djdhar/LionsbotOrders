package com.lionsbot.evaluation.dibyajyoti.service;

import com.lionsbot.evaluation.dibyajyoti.entity.Order;
import com.lionsbot.evaluation.dibyajyoti.repository.CustomerRepository;
import com.lionsbot.evaluation.dibyajyoti.repository.OrderRepository;
import com.lionsbot.evaluation.dibyajyoti.utils.JwtUtil;
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

    @Autowired
    JwtUtil jwtUtil;

    public List<Order> getOrders(String token) {
        if(jwtUtil.isAdminToken(token))
            return orderRepository.findAll();
        return null;
    }

    public List<Order> getOrdersByCustomerId(String customerId, String token) {
        if(jwtUtil.isAdminToken(token) ||
                jwtUtil.extractUsername(token.substring(7)).equals(String.valueOf(customerId)))
            return orderRepository.getOrdersByCustomerId(customerId);
        return null;
    }

    public Order addOrder(Order order, String token) {
        if(jwtUtil.isAdminToken(token)) return null;
        order.setOrderDate(new Date());
        order.setCustomerId((jwtUtil.extractUsername(token.substring(7))));
        return orderRepository.save(order);
    }

    public Order modifyOrder(String orderId, Order order, String token) {
        Optional<Order> dbOrder = orderRepository.findById(orderId);
        if(!dbOrder.isPresent()) return null;
        if(!jwtUtil.extractUsername(token.substring(7)).equals(String.valueOf(dbOrder.get().getCustomerId()))) return null;
        Order actualOrder = dbOrder.get();
        actualOrder.setTotalPrice(order.getTotalPrice());
        actualOrder.setNumberOfItems(order.getNumberOfItems());
        actualOrder.setOrderDate(order.getOrderDate());
        return orderRepository.save(actualOrder);
    }

    public String deleteOrder(String id, String token) {
        String userId = jwtUtil.extractUsername(token.substring(7));
        if(userId.endsWith("_ADMIN_")) {
            orderRepository.deleteById(id);
        }
        Optional<Order> dbOrder = orderRepository.findById(id);
        if(dbOrder.isPresent() && String.valueOf(dbOrder.get().getCustomerId()).equals(userId))
            orderRepository.deleteById(id);
        return "order removed !! " + id;
    }
}
