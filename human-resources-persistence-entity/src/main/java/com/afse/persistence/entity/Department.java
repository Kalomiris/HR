package com.afse.persistence.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 2145329698237298047L;

    @Id
    @GeneratedValue(generator = "DEPARTMENTSEQ")
    @SequenceGenerator(name = "DEPARTMENTSEQ", sequenceName = "DEPARTMENTSEQ", allocationSize = 1)
    private Long id;

    @NotNull(message = "Is empty!")
    @Size(max = 20, message = "size out of max")
    @Column(name = "name")
    private String name;

    /**
     * Version for optimistic locking
     */
    @Version
    @Column(name = "dbversion", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    @Valid
    @Embedded
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return id.equals(that.id);
    }

    @Override
    public String toString() {
        return "entity.Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
