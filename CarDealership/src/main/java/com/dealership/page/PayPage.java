package com.dealership.page;

import com.dealership.services.CarServices;
import com.dealership.services.PaymentServices;

import java.util.Scanner;

public class PayPage {

    public static void payMenu(){
        int userId =1;

        PaymentServices paymentServices = new PaymentServices();

        CarServices carServices = new CarServices();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Payment Page");
        System.out.println("What you like to do next? \n" +
                "1. Make a Payment \n" +
                "2. View remaining payments\n" +
                "3. View All Payments (Employee)\n" +
                "4. Return to home screen");
        int userChoice =  scanner.nextInt();

        switch(userChoice){


            case 1:

                System.out.println("Here are your car(s)");
                System.out.println(carServices.findAllOwned(userId));

                System.out.println("Which car would you like to submit a payment for?");
                int carId = scanner.nextInt();

                double currentPrice = paymentServices.findPrice(carId);
                System.out.println("The current price for this car is $" + String.format("%.2f",currentPrice +'.'));

                double monthlyPayments = paymentServices.calculatePayment(currentPrice);
                System.out.println("Your monthly payments are $"+ String.format("%.2f",monthlyPayments));

                System.out.println("How much would you like to pay today?");
                double payment = scanner.nextDouble();

                paymentServices.submitPayment(userId,carId,payment);
                System.out.println("Your payment of $" + String.format("%.2f",payment) + " has been applied. Thank you");

                payMenu();
                break;

            case 2:

                System.out.println("Here are your car(s)");
                System.out.println(carServices.findAllOwned(userId));

                System.out.println("Which car do you require the remaining payments from? \n" +
                        "please enter the car ID number.");
                int carIdR = scanner.nextInt();

                int paymentSum = paymentServices.sumOfPayments(carIdR, userId);
                int payRemain = paymentServices.remainingPayment(paymentSum);
                System.out.println("You have made "+ paymentSum + " payments so far.\n" +
                        "You have "+ payRemain + " payments remaining.");

                payMenu();
                break;

            case 3:

                System.out.println(paymentServices.findAll());

                payMenu();
                break;

            case 4:

                Welcome.welcome();

            default:
                System.out.println("Please choose a selection from the menu.");

                payMenu();


        }
    }
}
