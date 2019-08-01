package com.skowrondariusz.mechanicalprojectmanager.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class CommercialPart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private String orderSymbol;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manufacturer manufacturer;

    private int quantity;
    private Date orderDate;
    private Date deliveryDate;
    private long price;


    @ManyToOne(fetch = FetchType.LAZY)
    private PartsOrders partsOrders;

    public CommercialPart() {
    }

    public CommercialPart(String type, String orderSymbol, String name, Manufacturer manufacturer, int quantity, Date orderDate, Date deliveryDate, long price, PartsOrders partsOrders) {
        this.type = type;
        this.orderSymbol = orderSymbol;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.price = price;
        this.partsOrders = partsOrders;
    }

    public long getId() {
        return id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderSymbol() {
        return orderSymbol;
    }

    public void setOrderSymbol(String orderSymbol) {
        this.orderSymbol = orderSymbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public PartsOrders getPartsOrders() {
        return partsOrders;
    }

    public void setPartsOrders(PartsOrders partsOrders) {
        this.partsOrders = partsOrders;
    }
}
