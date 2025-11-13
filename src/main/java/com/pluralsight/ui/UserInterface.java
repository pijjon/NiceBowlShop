package com.pluralsight.ui;

import com.pluralsight.models.Donburi;
import com.pluralsight.models.Order;
import com.pluralsight.models.enums.DonburiSize;
import com.pluralsight.models.enums.DonburiType;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);
    private Order currentOrder;

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
        clearScreen();
            int response = askUserInt("""
                    WELCOME TO THE DON-GEON
                    1) Start an Order
                    2) Exit
                    
                    """);

            switch (response) {
                case 1:
                    clearScreen();
                    this.currentOrder = new Order(askUserStr("Please provide a name for the order: "));
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
        System.out.println("Welcome, " + currentOrder.getCustomer());
        boolean isRunning = true;
        while (isRunning) {
            clearScreen();
            int response = askUserInt("""
                    ORDER MENU
                    
                    1) ADD DONBURI
                    2) ADD DRINK
                    3) ADD SOUP
                    4) CHECKOUT
                    
                    """);

            switch (response) {
                case 1:
                    processAddDonburiRequest();
                    break;
                case 2:
                    // processAddDrinkRequest();
                    break;
                case 3:
                    // processAddSoupRequest();
                    break;
                case 4:
                    // processCheckOutRequest();
                    break;
                default:
                    System.out.println("Invalid input, try again!");
            }
        }
    }

    public void processAddDonburiRequest() {
        boolean isRunning = true;
        while (isRunning) {
            clearScreen();
            String response = askUserStr("""
                    PLEASE SELECT A DONBURI SIZE:
                    
                    S) SMALL DON
                    M) MEDIUM DON
                    L) LARGE DON
                    
                    X) NEVER MIND, GO BACK
                    
                    """);

            Donburi currentDonburi;

            switch (response.toLowerCase()) {
                case "s":
                    donburiBuilder(DonburiSize.SMALL);
                    break;

                case "m":
                    donburiBuilder(DonburiSize.MEDIUM);
                    break;

                case "l":
                    donburiBuilder(DonburiSize.LARGE);
                    break;

                case "x":
                    isRunning = false;
                    System.out.println("EXITING BACK TO ORDER MENU");
                    break;

                default:
                    System.out.println("INVALID SELECTION, PLEASE TRY AGAIN");
            }
        }
    }

    private Donburi donburiBuilder(DonburiSize size) {
        DonburiType donburiType = promptForDonburiType();
        Donburi currentDonburi = new Donburi(donburiType, size);
        currentOrder.addItem(currentDonburi);

        promptForDonburiToppings(currentDonburi);

        return currentDonburi;
    }

    private void promptForDonburiToppings(Donburi currentDonburi) {
        boolean isRunning = true;
        while (isRunning) {
            clearScreen();
            int response = askUserInt(String.format("""
                    PLEASE SELECT TOPPINGS TO ADD:
                    
                    [%s] %s
                    [%s] %s
                    [%s] %s
                    [%s] %s
                    [%s] %s
                    [%s] %s
                    
                    """

            ));
        }
    }

    private DonburiType promptForDonburiType() {

        boolean isRunning = true;

        DonburiType donburiType = null;

        while (isRunning) {
            clearScreen();
            int response = askUserInt(String.format("""
                            PLEASE SELECT A DONBURI TYPE:
                            
                            1) %s: %s
                            2) %s: %s
                            3) %s: %s
                            4) %s: %s
                            5) %s: %s
                            6) %s: %s
                            
                            """,
                    DonburiType.GYUDON.getDisplayName(), DonburiType.GYUDON.getDescription(),
                    DonburiType.BUTADON.getDisplayName(), DonburiType.BUTADON.getDescription(),
                    DonburiType.OYAKODON.getDisplayName(), DonburiType.OYAKODON.getDescription(),
                    DonburiType.UNAGI_DON.getDisplayName(), DonburiType.UNAGI_DON.getDescription(),
                    DonburiType.SAKE_DON.getDisplayName(), DonburiType.SAKE_DON.getDescription(),
                    DonburiType.YASAI_DON.getDisplayName(), DonburiType.YASAI_DON.getDescription()
            ));


            switch (response) {
                case 1 -> {
                    donburiType = DonburiType.GYUDON;
                    isRunning = false;
                }
                case 2 -> {
                    donburiType = DonburiType.BUTADON;
                    isRunning = false;
                }
                case 3 -> {
                    donburiType = DonburiType.OYAKODON;
                    isRunning = false;
                }
                case 4 -> {
                    donburiType = DonburiType.UNAGI_DON;
                    isRunning = false;
                }
                case 5 -> {
                    donburiType = DonburiType.SAKE_DON;
                    isRunning = false;
                }
                case 6 -> {
                    donburiType = DonburiType.YASAI_DON;
                    isRunning = false;
                }
                default -> {
                    System.out.println("Invalid selection, please try again.");
                    scanner.nextLine(); // clear scanner buffer
                }
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
        } catch (Exception e) {
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


    /*
    NOTE THIS DOES NOT WORK IN INTELLIJ, SO DEMO IN A TERMINAL
     */
    // method for clearing the console window
    // using this to make the CLI look more clean and interactive
    private void clearScreen() {
        // "\033[H" moves cursor to the first row
        // "\033[2J" clears everything past and underneath the cursor
        System.out.print("\033[H\033[2J");

        // apparently used to force data buffered in System.out to be immediately outputted
        System.out.flush();
    }


}
