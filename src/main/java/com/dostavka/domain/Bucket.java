package com.dostavka.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "buckets")
public class Bucket {
    public Bucket() {
    }

    public Bucket(int id, User user, List<Product> product) {
        this.id = id;
        this.user = user;
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bucket_seq")
    @SequenceGenerator(name = "bucket_seq", sequenceName = "buckets_id_seq", allocationSize = 1)
    private int id;

    @JsonBackReference
    @OneToOne(optional = false, mappedBy = "bucket")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "l_buckets_products",
            joinColumns = {@JoinColumn(name = "bucket_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> product;
}
