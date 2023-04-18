package com.dostavka.domain;

import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    public Product() {
    }

    public Product(int id, String title, double price, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.categories = categories;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "products_id_seq", allocationSize = 1)
    private int id;

    private String title;

    private double price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "l_products_categories",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Category> categories;
}
