package com.dealership.services;

import com.dealership.model.Car;

import com.dealership.repository.CarRepository;
import com.dealership.repository.CarRepositoryImp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarServices {
	
	private static final Logger LOG = LogManager.getFormatterLogger(CarServices.class);

    private CarRepository carRepository;

    public CarServices(){
        this.carRepository = new CarRepositoryImp();
    }

    public List<Car> findAllOnLot(){
       return this.carRepository.findAllOnLot();
    }

    public List<Car> findAllOwned(int userId){
        return this.carRepository.findAllOwned(userId);
    }

    public Car findById(int car_id){
        return this.carRepository.findById(car_id);
    }

    public void updateCarStatus(int car_id, int status){
        this.carRepository.updateCarStatus(car_id,status);
    }

    public void addToLot(Car car){
        this.carRepository.addToLot(car);
    }

    public void insertOwnedCar(Car car, int userId){this.carRepository.insertOwnedCar(car,userId);}

    public void removeFromLot(int id){
        this.carRepository.removeCarFromLot(id);
    }

    public void updateCarPrice(double price){
        this.carRepository.updateCarPrice(price);
    }

}
