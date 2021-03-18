package com.dealership.page;

import com.dealership.model.User;
import com.dealership.services.UserServices;

import java.util.Scanner;

public class UserPage {

    public static void userMenu(){

        UserServices userServices =  new UserServices();

        Scanner scanner = new Scanner(System.in);

        System.out.println("This is the User Menu");
        System.out.println("Please choose your next action.\n" +
                "1. Login to your account.\n" +
                "2. Register for an account\n" +
                "3. Return to home screen");

        final int userChoice = scanner.nextInt();

        switch (userChoice){

            case 1:
                System.out.println("Please provide your username");
                String username = scanner.next();
                System.out.println("Please provide your password");
                String password = scanner.next();

                User user = userServices.findByUsername(username);

                if (user != null){
                    if (password.equals(user.getPassword())){
                        System.out.println("Welcome back! "+ user.getFirstName());

                        CarPage.carMenu();
                    }
                    else {
                        System.out.println("There something wrong with the credentials you supplied.\n" +
                                "Please try again.");
                        userMenu();
                    }

                }

                break;

            case 2:
                System.out.println("Welcome new user!\n" + "Please provide your first name: ");
                String firstname =  scanner.next();
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
                newUser.addUser(new User(initUsername,initPassword,firstname,lastname,dlNumber,email));

                System.out.println("Welcome "+firstname+"! We hope you enjoy your experience with us!");

                userMenu();
                break;

            case 3:

                Welcome.welcome();

            default:

                System.out.println("Please select a menu option available above.");
                userMenu();

        }

    }

}
