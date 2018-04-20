package com.codecool.shop.model;

public class Address {
    private String address;
    private String city;
    private String country;
    private int zip;

    public Address(String address, String city, String country, int zip) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getZip() {
        return zip;
    }
}
