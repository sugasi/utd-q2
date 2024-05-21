package com.utd.q2.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Table(name = "orders")
public class Order {

    @Id
    private int orderId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    @Column(name = "date_of_sale")
    private LocalDate orderDate;

    @Column(name = "region")
    private String region;

    @Column(name = "quantity_sold")
    private int quantity;

    @Column(name = "discount")
    private double discount;

    @Column(name = "shipping_cost")
    private double shippingCost;

    @Column(name = "payment_method")
    private String paymentMethod;

}
