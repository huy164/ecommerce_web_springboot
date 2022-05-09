package com.backend.lavender.models;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToOne(mappedBy = "customer")
    private CustomerAccount customerAccount;

    public Customer() {
    }

    public Customer(int id, CustomerAccount customerAccount) {
        this.id = id;
        this.customerAccount = customerAccount;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomerAccount getCustomerAccount() {
        return this.customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public Customer id(int id) {
        setId(id);
        return this;
    }

    public Customer customerAccount(CustomerAccount customerAccount) {
        setCustomerAccount(customerAccount);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(customerAccount, customer.customerAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerAccount);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", customerAccount='" + getCustomerAccount() + "'" +
            "}";
    }

}
