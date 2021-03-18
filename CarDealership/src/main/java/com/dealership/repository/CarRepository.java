package com.dealership.repository;

import com.dealership.model.Car;

import java.util.List;

public interface CarRepository {

    List<Car> findAllOnLot();

    List<Car> findAllOwned(int userId);

    Car findById(int car_id);

    void updateCarStatus(int car_id, int status);

    void addToLot(Car car);

    void removeCarFromLot(int id);

    void updateCarPrice(double price);

    void insertOwnedCar(Car car,int userId);

}
