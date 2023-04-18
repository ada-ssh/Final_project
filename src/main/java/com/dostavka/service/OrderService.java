package com.dostavka.service;

import com.dostavka.domain.Order;
import com.dostavka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class OrderService {
    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(int id){
        return orderRepository.findById(id).get();
    }

    public ArrayList<Order> getAllOrders() {
        return (ArrayList<Order>) orderRepository.findAll();
    }

    public Order getOrderBySum(Double sum){
        return orderRepository.findOrderBySum(sum).get();
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrderById(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    public void deleteOrder (Order order) {
        orderRepository.delete(order);
    }
}
