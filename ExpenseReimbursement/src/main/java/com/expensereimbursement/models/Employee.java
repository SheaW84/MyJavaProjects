package com.expensereimbursement.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(generator = "employee_id" , strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1, name = "employee_id_seq")
    private int id;
    @Column(name = "employee_firstname")
    private String firstName;
    @Column(name = "employee_lastname")
    private String lastName;
    @Column(name = "employee_address")
    private String address;
    @Column(name="employee_email")
    private String email;
    @Column(name ="employee_password")
    private String password;
    @Column(name = "is_manager")
    private boolean isManager;
    @JoinColumn(name = "employee_manager_id")
    @ManyToOne
    private Employee manager;


    public Employee() {
        super();
    }

    public Employee(int id, String firstName, String lastName, String address, String email, String password, boolean isManager, Employee manager) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.isManager = isManager;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && isManager == employee.isManager &&
                manager == employee.manager &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(address, employee.address) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, email, password, isManager, manager);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password=" + email+'\''+
                ", isManager=" + isManager +
                ", manager=" + manager +
                '}';
    }
}


