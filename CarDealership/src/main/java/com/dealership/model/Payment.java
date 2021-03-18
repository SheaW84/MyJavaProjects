package com.dealership.model;

import java.io.Serializable;
import java.util.Objects;

public class Payment implements Serializable {
    private int id;
    private int user;
    private int car;
    private double amount;

    public Payment(){
        super();
    }

    public Payment(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Payment(int id, int user, int car, double amount){
        this.id = id;
        this.user = user;
        this.car = car;
        this.amount = amount;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && user == payment.user && car == payment.car && Double.compare(payment.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, car, amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id: " + id + "\n" +
                ", user: " + user + "\n" +
                ", car: " + car + "\n" +
                ", amount: $" + amount +
                "}\n\n";
    }
}
