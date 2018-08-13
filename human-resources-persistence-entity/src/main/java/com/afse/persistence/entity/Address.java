package com.afse.persistence.entity;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class Address implements Serializable {

    private static final long serialVersionUID = -6299108918042614223L;

    @NotNull(message = "Is empty!")
    @Size(max = 20, message = "size out of max")
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "country")
    private String country;

    @NotNull(message = "Is empty!")
    @Size(max = 20, message = "size out of max")
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "city")
    private String city;


    @NotNull(message = "Is empty!")
    @Size(max = 20, message = "size out of max")
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "street")
    private String street;

    @NotNull(message = "Is empty!")
    @Size(max = 10, message = "size out of max")
    @Pattern(regexp = "[0-9]+")
    @Column(name = "street_number")
    private String streetNumber;

    @NotNull(message = "Is empty!")
    @Size(max = 20, message = "size out of max")
    @Pattern(regexp = "[0-9]+")
    @Column(name = "zip")
    private String zip;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String street_number) {
        this.streetNumber = street_number;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!city.equals(address.city)) return false;
        if (!country.equals(address.country)) return false;
        if (!street.equals(address.street)) return false;
        if (!streetNumber.equals(address.streetNumber)) return false;
        return zip.equals(address.zip);
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", street='" + street + '\'' +
                ", StreetNumber='" + streetNumber + '\'' +
                ", zipCode='" + zip + '\'' +
                '}';
    }
}
