package com.dealership.repository;

import com.dealership.model.Payment;

import java.util.List;

public interface PaymentRepository {

    List<Payment> findAll();

    List<Payment> findAllForCar(int carId);

    void submitPayment(int userId, int carId, double amount);

    double findPrice (int carId);

    int sumOfPayments(int carId, int userId);

}
