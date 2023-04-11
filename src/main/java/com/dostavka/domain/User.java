package com.dostavka.domain;

import com.dostavka.annotation.IsArchive;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.security.cert.CertPathBuilder;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private int id;

    @Size(min = 2, max = 10)
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Pattern(regexp = "\\d{12}")
    @Column(name = "login")
    private String login;

    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    @Column(name = "email")
    private String email;

    @IsArchive
    @Column(name = "archive")
    private int archive;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;
}
