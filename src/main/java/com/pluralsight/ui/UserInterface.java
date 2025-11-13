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
        Order order = new Order(askUserStr("Please provide a name for the order: "));
        boolean isRunning = true;
        while (isRunning) {
            int response = askUserInt("""
                    ORDER MENU
                    
                    1) Add a NiceBowl
                    2) Add a Drink
                    3) Add Karaage
                    4) Confirm Order
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

    private Donburi donburiBuilder(DonburiSize size) {
        Donburi currentDonburi;
        DonburiType donburiType = promptForDonburiType();
        currentDonburi = new Donburi(donburiType, size);
        currentOrder.addItem(currentDonburi);
        return currentDonburi;
    }

    private DonburiType promptForDonburiType() {
        boolean isRunning = true;

        DonburiType donburiType = null;

        while (isRunning) {
            int response = askUserInt(String.format("""
                    PLEASE SELECT A DONBURI TYPE:
                   
                    1) %s: %s
                    2) %s: %s
                    3) %s: %s
                    4) %s: %s
                    5) %s: %s
                    6) %s: %s
                
                    """,
                    DonburiType.GYUDON.name(), DonburiType.GYUDON.getDescription(),
                    DonburiType.BUTADON.name(), DonburiType.BUTADON.getDescription(),
                    DonburiType.OYAKODON.name(), DonburiType.OYAKODON.getDescription(),
                    DonburiType.UNAGIDON.name(), DonburiType.UNAGIDON.getDescription(),
                    DonburiType.SAKEDON.name(), DonburiType.SAKEDON.getDescription(),
                    DonburiType.YASAIDON.name(), DonburiType.YASAIDON.getDescription()
            ));



            switch (response) {
                case 1 -> { donburiType = DonburiType.GYUDON; isRunning = false; }
                case 2 -> { donburiType = DonburiType.BUTADON; isRunning = false; }
                case 3 -> { donburiType = DonburiType.OYAKODON; isRunning = false; }
                case 4 -> { donburiType = DonburiType.UNAGIDON; isRunning = false; }
                case 5 -> { donburiType = DonburiType.SAKEDON; isRunning = false; }
                case 6 -> { donburiType = DonburiType.YASAIDON; isRunning = false; }
                default -> System.out.println("Invalid selection, please try again.");
            }
        }
        return donburiType;
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
        catch (Exception e) {
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
