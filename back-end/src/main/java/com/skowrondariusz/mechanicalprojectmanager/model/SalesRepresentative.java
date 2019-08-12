package com.skowrondariusz.mechanicalprojectmanager.model;

import javax.persistence.*;

@Entity
public class SalesRepresentative {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    private Manufacturer manufacturer;

    public SalesRepresentative() {
    }

    public SalesRepresentative(String name, String lastName, String email, Manufacturer manufacturer) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
