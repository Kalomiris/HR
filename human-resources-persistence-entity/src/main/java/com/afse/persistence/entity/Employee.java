package com.afse.persistence.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = -5851697754258867702L;

    @Id
    @GeneratedValue(generator = "EMPLOYEESEQ")
    @SequenceGenerator(name = "EMPLOYEESEQ", sequenceName = "EMPLOYEESEQ", allocationSize = 1)
    private Long id;

    @NotNull(message = "firstName field Is empty!")
    @Size(max = 20, message = "size out of max")
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "lastNmae field Is empty!")
    @Size(max = 20, message = "size out of max")
    @Pattern(regexp = "[a-zA-Z]+")
    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;


    @Valid
    @Embedded
    private Address address;

    @NotNull(message = "phoneNumber field is empty!")
    @Pattern(regexp = "^[0-9]+", message = "Wrong input!")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "email field is empty!")
    @Email(message = "email")
    @Column(name = "email")
    private String email;

    @NotNull(message = "salary field is empty!")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "salary")
    private double salary;


    @NotNull(message = "joinDate field is empty!")
    @Temporal(TemporalType.DATE)
    @Column(name = "join_date")
    private Date joinDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    public Long getId() {
        return id;
    }

    /**
     * Version for optimistic locking
     */
    @Version
    @Column(name = "dbversion", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private int version;

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return id.equals(employee.id);
    }

    @Override
    public String toString() {
        return "entity.Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}
