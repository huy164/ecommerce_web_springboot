package com.backend.lavender.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class Person {
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected String pictureUrl;
    protected Date   dob;

    public Person() {
    }

    public Person(String name, String email, String phoneNumber, String pictureUrl, Date dob) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pictureUrl = pictureUrl;
        this.dob = dob;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Person name(String name) {
        setName(name);
        return this;
    }

    public Person email(String email) {
        setEmail(email);
        return this;
    }

    public Person phoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return this;
    }

    public Person pictureUrl(String pictureUrl) {
        setPictureUrl(pictureUrl);
        return this;
    }

    public Person dob(Date dob) {
        setDob(dob);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(email, person.email) && Objects.equals(phoneNumber, person.phoneNumber) && Objects.equals(pictureUrl, person.pictureUrl) && Objects.equals(dob, person.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber, pictureUrl, dob);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", pictureUrl='" + getPictureUrl() + "'" +
            ", dob='" + getDob() + "'" +
            "}";
    }

}
