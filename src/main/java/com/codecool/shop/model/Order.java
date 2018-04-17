package com.codecool.shop.model;

public class Order {

    private enum Status {
        IN_PROGRESS,
        PAID
    }

    private String name;
    private String email;
    private String billingAddress;
    private String shippingAddress;
    private int phone;
    private Status status;
    ShoppingCart orderShoppingCart;

    public Order(String name, String email, String billingAddress, String shippingAddress, int phone, ShoppingCart shoppingCart) {
        this.orderShoppingCart = shoppingCart;
        this.name = name;
        this.email = email;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
        status = Status.IN_PROGRESS;
    }

    public void pay() {
        status = Status.PAID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

}
