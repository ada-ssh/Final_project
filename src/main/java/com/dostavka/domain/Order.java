package com.dostavka.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    public Order() {
    }

    public Order(int id, LocalDateTime created, User user, double sum, String address, List<Product> product, OrderStatus status) {
        this.id = id;
        this.created = created;
        this.user = user;
        this.sum = sum;
        this.address = address;
        this.product = product;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "orders_id_seq", allocationSize = 1)
    private int id;

    @CreationTimestamp
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double sum;

    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "l_orders_products",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> product;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
