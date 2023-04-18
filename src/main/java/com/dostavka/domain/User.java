package com.dostavka.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class User {
    public User() {
    }

    public User(int id, String name, String password, String login, String email, int archive, Role role, Bucket bucket) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.login = login;
        this.email = email;
        this.archive = archive;
        this.role = role;
        this.bucket = bucket;
    }

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

    @Column(name = "archive")
    private int archive;

    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;
}
