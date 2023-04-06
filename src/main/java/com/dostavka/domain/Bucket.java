package com.dostavka.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "buckets")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bucket_seq")
    @SequenceGenerator(name = "bucket_seq", sequenceName = "bucket_seq", allocationSize = 1)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "l_buckets_products",
            joinColumns = {@JoinColumn(name = "bucket_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> product;



}
