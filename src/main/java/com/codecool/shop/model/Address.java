package com.codecool.shop.model;

/**
 * Records and stores a custom Address type data.
 * Address consista of city, country, zip code, and street address.
 */
public class Address {
    private String address;
    private String city;
    private String country;
    private int zip;

    /**
     * Generates an Address type object.
     *
     * @param address: street address as a String.
     * @param city:    city as a String.
     * @param country: country as a Sring.
     * @param zip:     zip code as an integer.
     */
    public Address(String address, String city, String country, int zip) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }

    /**
     * Returns the street address of the Address type data as a String.
     *
     * @return: street address as String.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the city of the Address type data as a String.
     *
     * @return: city as String.
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the country of the Address type data as a String.
     *
     * @return: country as String.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns the zip code of the Address type data as an integer.
     *
     * @return: zip code as integer.
     */
    public int getZip() {
        return zip;
    }
}
