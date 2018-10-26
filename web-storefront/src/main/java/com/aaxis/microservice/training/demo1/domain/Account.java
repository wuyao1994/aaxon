package com.aaxis.microservice.training.demo1.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;

@Document(indexName = "mysql", type = "account", refreshInterval = "0s")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String username;
    private String password;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role   role;



    public Long getId() {
        return id;
    }



    public void setId(Long pId) {
        id = pId;
    }



    public String getUsername() {
        return username;
    }



    public void setUsername(String pUsername) {
        username = pUsername;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String pPassword) {
        password = pPassword;
    }



    public Role getRole() {
        return role;
    }



    public void setRole(Role pRole) {
        role = pRole;
    }
}
