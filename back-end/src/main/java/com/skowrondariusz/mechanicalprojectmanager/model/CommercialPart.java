package com.skowrondariusz.mechanicalprojectmanager.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CommercialPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commercial_part_id")
    private long id;
    private String type;
    private String orderSymbol;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    private int quantity;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    private Date deliveryDate;
    private long price;


    @ManyToOne(fetch = FetchType.LAZY)
    private PartsOrder partsOrder;

    public CommercialPart() {
    }

    public CommercialPart(String type, String orderSymbol, String name, int quantity, Date orderDate, Date deliveryDate, long price, PartsOrder partsOrder) {
        this.type = type;
        this.orderSymbol = orderSymbol;
        this.name = name;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.price = price;
        this.partsOrder = partsOrder;
    }

    public CommercialPart(String type, String orderSymbol, String name, Manufacturer manufacturer, int quantity, Date orderDate, Date deliveryDate, long price, PartsOrder partsOrder) {
        this.type = type;
        this.orderSymbol = orderSymbol;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.price = price;
        this.partsOrder = partsOrder;
    }

    public CommercialPart(String type, String orderSymbol, String name, Manufacturer manufacturer, Invoice invoice, int quantity, Date orderDate, Date deliveryDate, long price, PartsOrder partsOrder) {
        this.type = type;
        this.orderSymbol = orderSymbol;
        this.name = name;
        this.manufacturer = manufacturer;
        this.invoice = invoice;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.price = price;
        this.partsOrder = partsOrder;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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

    public PartsOrder getPartsOrder() {
        return partsOrder;
    }

    public void setPartsOrder(PartsOrder partsOrder) {
        this.partsOrder = partsOrder;
    }


    @Override
    public String toString() {
        return "CommercialPart{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", orderSymbol='" + orderSymbol + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer=" + manufacturer +
                ", invoice=" + invoice +
                ", quantity=" + quantity +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", price=" + price +
                ", partsOrder=" + partsOrder +
                '}';
    }


    public String inquiryCommercialPart() {
        return name + "\t" + orderSymbol + "\t" + quantity;
    }
}




