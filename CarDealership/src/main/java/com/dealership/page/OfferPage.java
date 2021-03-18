package com.dealership.page;

import com.dealership.model.Car;
import com.dealership.model.Offer;
import com.dealership.model.User;
import com.dealership.services.CarServices;
import com.dealership.services.OfferServices;
import com.dealership.services.UserServices;

import java.util.Scanner;

public class OfferPage {


    public static void offerMenu(){

        int userId =0;



        Scanner scanner = new Scanner(System.in);
        CarServices viewLot = new CarServices();
        OfferServices offerServices = new OfferServices();



        System.out.println("This is the offer page");
        System.out.println("Please make a selection \n" +
                "1. Make an offer for a car\n" +
                "2. View offers (Employee)\n" +
                "3. Change offer status (Employee)\n" +
                "4. Return to home screen");
        int userChoice = scanner.nextInt();

        switch (userChoice){

            case 1:

                System.out.println(viewLot.findAllOnLot());
                System.out.println("Please enter the id number for the car you wish to bid on");
                int carId = scanner.nextInt();
                System.out.println("Enter the amount you wish to bid");
                double bidAmount = scanner.nextDouble();

                offerServices.submitOffer(new Offer(bidAmount,userId,carId));
                System.out.println("Thank you, your offer of $"+ bidAmount +" has been placed.");

                offerMenu();

                break;

            case 2:

                System.out.println(offerServices.findAll());
                System.out.println("Would you like to accept or reject any offers presented?\n y/n");
                String decisions = scanner.next();

                if (decisions.equals("y")){
                    System.out.println("Please enter the offer id you wish to update.");
                    int offerId = scanner.nextInt();
                    System.out.println("What would you update the status to?\n " +
                            "1. Pending\n" +
                            "2. Accept\n" +
                            "3. Reject");
                    int status = scanner.nextInt();

                    offerServices.updateOfferStatus(offerId,status); // updates the status of an offer

                    switch(status)  {
                        case 2:

                        Offer updateOffer = offerServices.findById(offerId);

                        /**
                         * System has to update the status of the car from for sale to owned automatically
                         */

                        CarServices carServices = new CarServices();
                        Car updateCar = carServices.findById(updateOffer.getCarId()); // returns car

                        carServices.updateCarStatus(updateCar.getId(), 2);
                        offerServices.rejectPendingOffers(updateCar.getId());

                        carServices.insertOwnedCar(updateCar, updateOffer.getUserId());


                        case 3:

                            offerServices.updateOfferStatus(offerId,status);


                        default:

                            System.out.println("Please make a choice from the menu provided");
                            offerMenu();



                    }

                }

                break;

        }

    }
}

