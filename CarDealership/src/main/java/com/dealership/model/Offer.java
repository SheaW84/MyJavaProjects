package com.dealership.model;


import java.util.Objects;

public class Offer {

    private int id;
    private double amount;
    private int userId;
    private int carId;
    private int status;

    public Offer(){
        super();
    }

public Offer (double amount, int  userId, int carId){
        this.amount = amount;
        this.carId = carId;
        this.userId = userId;
}

    public Offer(int id, double amount, int userId, int carId, int status) {
        this.id = id;
        this.amount = amount;
        this.userId = userId;
        this.carId = carId;
        this.status = status;
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

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id && Double.compare(offer.amount, amount) == 0 && userId == offer.userId && carId == offer.carId && status == offer.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, userId, carId, status);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", amount=" + amount +
                ", userId=" + userId +
                ", carId=" + carId +
                ", status=" + status +
                "}\n";
    }
}
