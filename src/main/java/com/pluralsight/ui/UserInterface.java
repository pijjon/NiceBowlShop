package com.pluralsight.ui;

import com.pluralsight.models.Order;

import java.util.Scanner;

public class UserInterface {
    public static Scanner scanner = new Scanner(System.in);

    public UserInterface() {
    }

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            int response = askUserInt("""
                    WELCOME TO NICE BOWLS
                    
                    1) Start an Order
                    2) Exit
             
                    """);

            switch (response) {
                case 1:
                    orderScreen();
                    break;
                case 2:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid selection, please try again!");
            }
        }
    }

    private void orderScreen() {
        boolean isRunning = true;
        while (isRunning) {
            this.order = new Order();
            int response = askUserInt("""
                    ORDER MENU
                    
                    1) Order a NiceBowl
                    2) Order a Drink
                    3) Order a serving of Karaage
                    
                    """);

            switch (response) {
                case 1:
                    processNiceBowlOrder();
                    break;
                case 2:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;
            }
        }
    }

    public String askUserStr(String question) {
        try {
            System.out.println(question);
            String response = scanner.nextLine();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public int askUserInt(String question) {
        try {
            System.out.println(question);
            int response = scanner.nextInt();
            scanner.nextLine();
            return response;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public double askUserDouble(String question) {
        try {
            System.out.println(question);
            double response = scanner.nextDouble();
            scanner.nextLine();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
