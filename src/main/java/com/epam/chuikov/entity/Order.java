package com.epam.chuikov.entity;

import java.sql.Date;


public class Order {
    private Integer id;
    private Date date;
    private String details;
    private OrderStatus status;
    private User user;
    private String payWay;
    private String deliveryAddress;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", details='" + details + '\'' +
                ", status=" + status +
                ", user=" + user +
                ", payWay='" + payWay + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }
}
