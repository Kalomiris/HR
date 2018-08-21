package com.afse.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "location")
public class Location implements Serializable {

    private static final long serialVersionUID = -960055054621285474L;

    @Id
    @GeneratedValue(generator = "LOCATIONSEQ")
    @SequenceGenerator(name = "LOCATIONSEQ", sequenceName = "LOCATIONSEQ", allocationSize = 1)
    private Long id;

    @NotNull(message = "Is empty!")
    @Size(max = 20, message = "size out of max")
    @Column(name = "country")
    private String country;

    @NotNull(message = "Is empty!")
    @Size(max = 20, message = "size out of max")
    @Column(name = "city")
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location that = (Location) o;

        if (!id.equals(that.id)) return false;
        if (!city.equals(that.city)) return false;
        return country.equals(that.country);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
