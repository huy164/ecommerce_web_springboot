package com.backend.lavender.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@Entity
@Table(name="customer_account")
public class CustomerAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String username;
    private String password;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
