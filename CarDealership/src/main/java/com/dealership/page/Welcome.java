package com.dealership.page;

import com.dealership.model.Car;
import com.dealership.model.Offer;
import com.dealership.model.User;
import com.dealership.services.CarServices;
import com.dealership.services.OfferServices;
import com.dealership.services.PaymentServices;
import com.dealership.services.UserServices;

import java.util.Scanner;

public class Welcome {

    public static User user;

    public static void welcome() {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("This is the welcome menu.");
        System.out.println("Please choose your next action\n" +
                "1. For the user menu.\n " +
                "2. For the car menu.\n " +
                "3. For the offer menu.\n " +
                "4. For the payment menu.\n"+
                "5. Log out");

        final int userChoice = scanner.nextInt();

        switch (userChoice) {

            case 1:

               userMenu();
               break;

            case 2:

                carMenu();
                break;

            case 3:

                offerMenu();
                break;

            case 4:
            	
                payMenu();
                break;
                
            case 5:
            	
            	if(user!=null) {
            		user = null;
            		welcome();
            	}else{
            		System.out.println("You need to be logged in to log out");
            		welcome();
            	}
            	break;

            default:
                welcome();
        }

    }
    
    public static void userMenu(){

        UserServices userServices = new UserServices();

        Scanner scanner = new Scanner(System.in);

        System.out.println("This is the User Menu");
        System.out.println("Please choose your next action.\n" +
                "1. Login to your account.\n" +
                "2. Register for an account\n" +
                "3. Return to home screen");

        final int userMenuChoice = scanner.nextInt();

        switch (userMenuChoice) {

            case 1:
                System.out.println("Please provide your username");
                String username = scanner.next();
                System.out.println("Please provide your password");
                String password = scanner.next();

                user = userServices.findByUsername(username);

                if (user != null) {
                    if (password.equals(user.getPassword())) {
                        System.out.println("Welcome back! " + user.getFirstName());

                        carMenu();

                    } else {
                        System.out.println("There something wrong with the credentials you supplied.\n" +
                                "Please try again.");
                        userMenu();
                    }

                }

                break;

            case 2:
                System.out.println("Register for account.");
                System.out.println("Welcome new user!\n" + "Please provide your first name: ");
                String firstname = scanner.next();
                System.out.println("Your last name: ");
                String lastname = scanner.next();
                System.out.println("Your preferred username: ");
                String initUsername = scanner.next();
                System.out.println("A strong password you can remember:");
                String initPassword = scanner.next();
                System.out.println("Your drivers license number");
                String dlNumber = scanner.next();
                System.out.println("Finally, your email address: ");
                String email = scanner.next();

                UserServices newUser = new UserServices();
                newUser.addUser(new User(initUsername, initPassword, firstname, lastname, dlNumber, email));

                System.out.println("Welcome " + firstname + "! We hope you enjoy your experience with us!");

                userMenu();
                break;

            case 3:

                welcome();
                break;

            default:

                System.out.println("Please select a menu option available.");
                userMenu();
        }

    }

    public static void carMenu(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Car Lot");

        System.out.println("Please choose the next action you wish to take. \n" +
                "1. View cars on the lot. \n" +
        		"2. View owned cars.\n"+
                "3. Submit offer for a car. \n" +
                "4. Add cars to the lot. (Employee) \n" +
                "5. Remove a car from the lot.(Employee)\n" +
                "6. Return to home screen\n"+
                "7. Log out");

        int userLotChoice = scanner.nextInt();

        CarServices viewCars = new CarServices();

        switch (userLotChoice) {

            case 1:
                if(user!=null && user.getRole() == 1) {

                    System.out.println(viewCars.findAllOnLot());
                    
                    System.out.println("Would you like to bid an offer for a car?\n Please enter y/n for yes or no.");
                    String decisions = scanner.next();
                    if (decisions.equalsIgnoreCase("y")) {
                        offerMenu();
                    } else if (decisions.equalsIgnoreCase("n")) {
                        carMenu();
                    } else {
                        System.out.println("Something went wrong, please try again");
                        carMenu();
                    }
                } else {
                    System.out.println("You are not authorized to do this.\n");
                    carMenu();
                }

                break;
                
            case 2:
            	if(user!=null) {
            		System.out.println(viewCars.findAllOwned(user.getId()));
            		carMenu();
            	} else {
            		System.out.println("You must be logged in to do this.\n");
            		carMenu();
            	}
            	break;

            case 3:
                if(user!=null && user.getRole()==1) {

                    CarServices viewLot = new CarServices();
                    OfferServices offerServices = new OfferServices();

                    System.out.println(viewLot.findAllOnLot());
                    System.out.println("Please enter the id number for the car you wish to bid on");
                    int carId = scanner.nextInt();
                    System.out.println("Enter the amount you wish to bid");
                    double bidAmount = scanner.nextDouble();

                    offerServices.submitOffer(new Offer(bidAmount, user.getId(), carId));
                    System.out.println("Thank you, your offer of $" + bidAmount + " has been placed.");
                } else {
                    System.out.println("You are not authorized to do this.");
                    userMenu();
                }

                carMenu();

                break;

            case 4:
                if(user!=null && user.getRole() == 2) {
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

                    Car newCar = new Car(carYear, carMake, carModel, carColor, carPrice);
                    viewCars.addToLot(newCar);
                    System.out.println(viewCars.findAllOnLot());
                    System.out.println("Please verify that the car has made it to the lot. y/n");
                    String inLot = scanner.next();

                    if (inLot.equals("y")) {
                        System.out.println("Great! What do want to do next?");
                        carMenu();
                    } else {
                        System.out.println("Sorry something must've went wrong try again");
                        carMenu();
                    }
                } else {
                    System.out.println("You are not authorized for this action");
                    carMenu();
                }

                carMenu();

                break;

            case 5:
                if(user!=null && user.getRole()==2) {

                    System.out.println(viewCars.findAllOnLot());
                    System.out.println("Please provide the id of the car you need to remove");
                    int carChoice = scanner.nextInt();

                    viewCars.removeFromLot(carChoice);
                    System.out.println(viewCars.findAllOnLot());

                    System.out.println("Please verify that the indicated car has been removed from the lot.");

                    carMenu();
                } else {
                    System.out.println("You are not authorized for this");
                    carMenu();
                }

                break;

            case 6:

                welcome();
                break;
                
            case 7:
            	 
            	user = null;
            	welcome();
            	break;

            default:
                System.out.println("Please choose one of the valid options provided");
                carMenu();
        }

    }

    public static void offerMenu(){

        Scanner scanner = new Scanner(System.in);
        CarServices viewLot = new CarServices();
        OfferServices offerServices = new OfferServices();

        System.out.println("This is the offer page");
        System.out.println("Please make a selection \n"+
                "1. Make an offer for a car\n"+
                "2. View offers (Employee)\n"+
                "3. Return to home screen\n"+
                "4. Log out");
        
        int userOfferChoice = scanner.nextInt();

        switch (userOfferChoice) {

            case 1:
                if (user!=null && user.getRole() == 1) {
                    System.out.println(viewLot.findAllOnLot());
                    System.out.println("Please enter the id number for the car you wish to bid on");
                    int carId = scanner.nextInt();
                    System.out.println("Enter the amount you wish to bid");
                    double bidAmount = scanner.nextDouble();

                    offerServices.submitOffer(new Offer(bidAmount, user.getId(), carId));
                    System.out.println("Thank you, your offer of $" + bidAmount + " has been placed.");

                    offerMenu();
                } else{
                    System.out.println("You are not authorized for this action");
                    offerMenu();
                }

                break;

            case 2:

                if (user!=null && user.getRole() == 2) {
                    System.out.println(offerServices.findAll());
                    System.out.println("Would you like to accept or reject any offers presented?\n y/n");
                    String decisions = scanner.next();

                    if (decisions.equals("y")) {
                        System.out.println("Please enter the offer id you wish to update.");
                        int offerId = scanner.nextInt();
                        System.out.println("What would you update the status to?\n " +
                                "1. Pending\n" +
                                "2. Accept\n" +
                                "3. Reject");
                        int status = scanner.nextInt();

                        offerServices.updateOfferStatus(offerId, status); // updates the status of an offer

                        switch (status) {
                            case 1:
                                offerServices.updateOfferStatus(offerId, status);
                                offerMenu();
                                break;

                            case 2:

                                Offer acceptOffer = offerServices.findById(offerId);

                                /**
                                 * System has to update the status of the car from for sale to owned automatically
                                 */

                                CarServices carServices = new CarServices();
                                Car updateCar = carServices.findById(acceptOffer.getCarId()); // returns car

                                carServices.updateCarStatus(updateCar.getId(), 2);
                                offerServices.rejectPendingOffers(updateCar.getId());

                                carServices.insertOwnedCar(updateCar, acceptOffer.getUserId());
                                offerMenu();
                                break;

                            case 3:

                                offerServices.updateOfferStatus(offerId, status);
                                offerMenu();
                                break;

                            default:

                                System.out.println("Please make a choice from the menu provided");
                                offerMenu();
                        }
                    }
                }else{
                    System.out.println( "You are not authorized");
                    offerMenu();
                }
                
            case 3:
            	
            	welcome();
            	break;
            	
            case 4:
            	
            	user = null;
            	welcome();
            	break;

            default:
                System.out.println("Please make a choice from the menu provided");
                offerMenu();
        }
    }

    public static void payMenu() {

        Scanner scanner = new Scanner(System.in);

        PaymentServices paymentServices = new PaymentServices();

        CarServices carServices = new CarServices();

        System.out.println("Payment Page");
        System.out.println("What you like to do next? \n" +
                "1. Make a Payment \n" +
                "2. View remaining payments\n" +
                "3. View All Payments (Employee)\n" +
                "4. Return to home screen");
        int userPayChoice =  scanner.nextInt();

        switch(userPayChoice) {

            case 1:
                try {
                    if (user!=null || user.getRole() == 1) {

                        System.out.println("Here are your car(s)");
                        System.out.println(carServices.findAllOwned(user.getId()));

                        System.out.println("Which car would you like to submit a payment for?");
                        int carId = scanner.nextInt();

                        double currentPrice = paymentServices.findPrice(carId);
                        System.out.println("The current price for this car is $" + String.format("%.2f", currentPrice + '.'));

                        double monthlyPayments = paymentServices.calculatePayment(currentPrice);
                        System.out.println("Your monthly payments are $" + String.format("%.2f", monthlyPayments));

                        System.out.println("How much would you like to pay today?");
                        double payment = scanner.nextDouble();

                        paymentServices.submitPayment(user.getId(), carId, payment);
                        System.out.println("Your payment of $" + String.format("%.2f", payment) + " has been applied. Thank you");
                    } else {
                        System.out.println("You must be logged in to do that");
                        payMenu();
                    }
                }catch(NullPointerException e){
                    System.out.println("You must be logged in to do that");
                    payMenu();
                }

                break;

            case 2:
                try {
                    if (user.getId() != 0 && user.getRole() == 1) {

                        System.out.println("Here are your car(s)");
                        System.out.println(carServices.findAllOwned(user.getId()));

                        System.out.println("Which car do you require the remaining payments from? \n" +
                                "please enter the car ID number.");
                        int carIdR = scanner.nextInt();

                        int paymentSum = paymentServices.sumOfPayments(carIdR, user.getId());
                        int payRemain = paymentServices.remainingPayment(paymentSum);
                        System.out.println("You have made " + paymentSum + " payments so far.\n" +
                                "You have " + payRemain + " payments remaining.");

                        payMenu();
                    } else {
                        System.out.println("You are not authorized for this action");
                        payMenu();
                    }
                }catch(NullPointerException e){
                    System.out.println("You must be logged in to do that");
                    payMenu();
                }

                break;

            case 3:
                if(user.getId()!=0 && user.getRole()==2 ) {

                    System.out.println(paymentServices.findAll());

                    payMenu();
                }else{
                    System.out.println("You are not authorized for this action");
                    payMenu();
                }
                break;

            case 4:

                welcome();

            default:
                System.out.println("Please choose a selection from the menu.");

                payMenu();
        }

    }
}
