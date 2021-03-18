package com.dealership.services;

import com.dealership.model.Payment;
import com.dealership.repository.PaymentRepository;
import com.dealership.repository.PaymentRepositoryImpl;

import java.util.List;

public class PaymentServices {


   private PaymentRepository paymentRepository;

   public PaymentServices(){
       this.paymentRepository = new PaymentRepositoryImpl();
   }

   public List<Payment> findAll(){
       return this.paymentRepository.findAll();
   }

   public List<Payment> findAllForCar (int carId){
      return this.paymentRepository.findAllForCar(carId);
   }

   public void submitPayment(int userId, int carId, double amount){
       if (amount != 0 || amount > 0) {
           this.paymentRepository.submitPayment(userId,carId,amount);
       } else
           System.out.println("Payment must be more than $ 0.00");
   }

   public double findPrice(int carId){
       return this.paymentRepository.findPrice(carId);
   }

   public int sumOfPayments(int carId, int userId){
       return this.paymentRepository.sumOfPayments(carId,userId);
   }


   public double calculatePayment(double price){
       if (price != 0 ) {
           return price / 60;
       } else
       System.out.println("Please enter valid price / months");
       return 0;
   }

    public int remainingPayment(int paymentSum) {
      return 60 - paymentSum;
    }





}
