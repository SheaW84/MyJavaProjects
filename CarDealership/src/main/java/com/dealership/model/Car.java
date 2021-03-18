package com.dealership.model;

import java.util.Objects;

public class Car {

    private int id;
    private int year;
    private String make;
    private String model;
    private String color;
    private double price;
    private int status;
    private int ownerId;

    public Car() {
    }

    public Car(int year, String make, String model, String color, double price){
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public Car (int id , int year, String make, String model, String color, double price, int status){
        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.price = price;
        this.status =  status;
    }

    public Car (int id , int year, String make, String model, String color, double price, int status, int ownerId){
        this.id = id;
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.price = price;
        this.status =  status;
        this.ownerId = ownerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && year == car.year && Double.compare(car.price, price) == 0 && Objects.equals(make, car.make) && Objects.equals(model, car.model) && Objects.equals(color, car.color) && Objects.equals(status, car.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, make, model, color, price, status);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", year=" + year +
                ", make='" + make + "'\n" +
                ", model='" + model + "'\n" +
                ", color='" + color + "'\n"+
                ", price: $" + String.format("%.2f",price) +
                ", status=" + status +
                "}\n\n";
    }
}
