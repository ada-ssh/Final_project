package com.dostavka.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_details_seq")
    @SequenceGenerator(name = "order_details_seq", sequenceName = "order_details_seq", allocationSize = 1)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double amount;

    private double price;


}
