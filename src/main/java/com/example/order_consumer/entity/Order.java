package com.example.order_consumer.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")  // map đúng với tên cột trong DB
    private String productName;

    private int quantity;
    private double price;

    public Order() {}

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // toString() tiện debug
    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", productName='" + productName + '\'' +
               ", quantity=" + quantity +
               ", price=" + price +
               '}';
    }
}
