package com.dealership.page;

import com.dealership.model.Car;
import com.dealership.services.CarServices;

import java.util.Scanner;

public class CarPage {

    public static void carMenu(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Car Lot");

        System.out.println("Please choose the next action you wish to take. \n" +
                "1. View cars on the lot. \n" +
                "2. Submit offer for a car. \n"+
                "3. Add cars to the lot. (Employee) \n" +
                "4. Remove a car from the lot.(Employee)\n" +
                "5. Return to home screen");

        int userChoice = scanner.nextInt();

        CarServices viewCars = new CarServices();

        switch(userChoice){

            case 1:

                System.out.println(viewCars.findAllOnLot());
                System.out.println("Would you like to bid an offer for a car?\n y/n");
                String decisions = scanner.next();
                if(decisions.equalsIgnoreCase("y")){
                    OfferPage.offerMenu();
                }else if(decisions.equalsIgnoreCase("n")){
                    CarPage.carMenu();
                }else{
                    System.out.println("Something went wrong, please try again");
                    CarPage.carMenu();
                }

                break;

            case 2:

                OfferPage.offerMenu();

                break;

            case 3:
                System.out.println("Adding a car to the lot");
                System.out.println("Please provide the car year");
                int carYear = scanner.nextInt();
                System.out.println("What is the car make?");
                String carMake = scanner.next();
                System.out.println("What is the car model?");
                String carModel = scanner.next();
                System.out.println("What is the car color?");
                String carColor = scanner.next();
                System.out.println("Provide current asking price:");
                double carPrice = scanner.nextDouble();

               Car newCar = new Car(carYear,carMake,carModel,carColor,carPrice);
               viewCars.addToLot(newCar);
                System.out.println(viewCars.findAllOnLot());
                System.out.println("Please verify that the car has made it to the lot. y/n");
                String inLot = scanner.next();

                if(inLot.equals("y")){
                    System.out.println("Great! What do want to do next?");
                    carMenu();
                } else {
                    System.out.println("Sorry something must've went wrong try again");
                    carMenu();
                }

                carMenu();

                break;

            case 4:

                System.out.println(viewCars.findAllOnLot());
                System.out.println("Please provide the id of the car you need to remove");
                int carChoice = scanner.nextInt();

                viewCars.removeFromLot(carChoice);
                System.out.println(viewCars.findAllOnLot());

                System.out.println("Please verify that the indicated car has been removed from the lot.");

                break;

            case 5:

                Welcome.welcome();

            default:
                System.out.println("Please choose one of the valid options provided");
                carMenu();

        }

    }
}
