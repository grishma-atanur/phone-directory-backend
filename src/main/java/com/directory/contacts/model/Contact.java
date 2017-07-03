package com.directory.contacts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 * Created by hp on 7/3/2017.
 */
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String Email;

    @Column
    private Long Phone;

    //Default Constructor for JPA
    public Contact() {
    }

    public Contact(ContactBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.Email = builder.Email;
        this.Phone = builder.Phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Phone=" + Phone +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Long getPhone() {
        return Phone;
    }

    public void setPhone(Long phone) {
        Phone = phone;
    }

    public static class ContactBuilder {
        private String firstName;
        private String lastName;
        private String Email;
        private Long Phone;

        public ContactBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public ContactBuilder withEmail(String Email) {
            this.Email = Email;
            return this;

        }

        public ContactBuilder withPhone(Long Phone) {
            this.Phone = Phone;
            return this;
        }

        public Contact build() {
            return new Contact(this);


        }
    }
}