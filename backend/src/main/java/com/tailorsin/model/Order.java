package com.tailorsin.model;

public class Order {
    private String id;
    private String customerName;
    private String item;
    private int price;
    private String status;
    private String deliveryStatus;
    private String address;
    private String rider;

    public Order() {
    }

    public Order(String id, String customerName, String item, int price, String status, String deliveryStatus, String address, String rider) {
        this.id = id;
        this.customerName = customerName;
        this.item = item;
        this.price = price;
        this.status = status;
        this.deliveryStatus = deliveryStatus;
        this.address = address;
        this.rider = rider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRider() {
        return rider;
    }

    public void setRider(String rider) {
        this.rider = rider;
    }
}
