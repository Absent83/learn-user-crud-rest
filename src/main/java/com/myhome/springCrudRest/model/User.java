package com.myhome.springCrudRest.model;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public User() {
    }

    public User(String name, String email) { //todo какие требования у Hibernate к полям / геттерам и сеттерам / конструкторам
        this.name = name;
        this.email = email;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
