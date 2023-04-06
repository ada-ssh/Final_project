package com.dostavka.domain;

import lombok.Builder;
import lombok.Data;


import javax.persistence.*;
import java.security.cert.CertPathBuilder;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "archive")
    private boolean archive;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "address")
    private String address;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Bucket bucket;
}
