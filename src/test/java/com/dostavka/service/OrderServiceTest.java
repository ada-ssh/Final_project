package com.dostavka.service;

import com.dostavka.domain.Order;
import com.dostavka.domain.OrderStatus;
import com.dostavka.domain.User;
import com.dostavka.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    private Order order;

    private List<Order> orders;

    @BeforeEach
    void setOrder() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository);
        order = new Order(1, LocalDateTime.now(), new User(), 12.37, "Surganova, 33", new ArrayList<>(), OrderStatus.NEW);
        orders = new ArrayList<>();
        orders.add(order);
        orderRepository.save(order);
    }

    @Test
    void getAllOrders() {
        when(orderRepository.findAll()).thenReturn(orders);
        Optional<List<Order>> optional = Optional.ofNullable(orderService.getAllOrders());
        assertTrue(optional.isPresent());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void createOrder() {
        Order newOrder = new Order(1, LocalDateTime.now(), new User(), 12.37, "Surganova, 33", new ArrayList<>(), OrderStatus.NEW);
        orderService.createOrder(newOrder);
        verify(orderRepository, times(2)).save(ArgumentMatchers.any(Order.class));
    }
}