package com.brainmatics.pos.core.employee;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {
    private final String street;
    private final String zipCode;
    private final String country;

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public Address(String street, String zipCode, String country) {
        this.street = street;
        this.zipCode = zipCode;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipCode, country);
    }
}
