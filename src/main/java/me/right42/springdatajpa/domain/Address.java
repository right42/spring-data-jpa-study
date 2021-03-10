package me.right42.springdatajpa.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String zipcode;

    private String street;

    private String city;

    private String state;
}
