package com.dealership.model;

import java.util.Objects;

public class User {

    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dlNumber;
    private String email;
    private int role;

    public User(){
     super();
    }

    public User ( int id, String username, String password, String firstName, String lastName, String dlNumber, String email, int role){
        this.id = id;
        this.username = username;
        this.password=password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dlNumber = dlNumber;
        this.email = email;
        this.role = role;
    }

    public User(String initUsername, String initPassword, String firstname, String lastname, String dlNumber, String email) {
        this.username= initUsername;
        this.password = initPassword;
        this.firstName = firstname;
        this.lastName = lastname;
        this.dlNumber = dlNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getDlNumber(){return dlNumber;}

    public void setDlNumber(String dlNumber) {this.dlNumber=dlNumber;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(dlNumber, user.dlNumber) &&
                Objects.equals(email, user.email) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName,dlNumber, email, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Driver's License='" + dlNumber + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
