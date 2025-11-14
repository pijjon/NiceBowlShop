package com.pluralsight.ui;

import com.pluralsight.models.Donburi;
import com.pluralsight.models.Order;
import com.pluralsight.models.Topping;
import com.pluralsight.models.enums.DonburiSize;
import com.pluralsight.models.enums.DonburiType;
import com.pluralsight.models.enums.ToppingItem;
import com.pluralsight.models.enums.ToppingType;

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
                    pause(2000);
            }
        }
    }

    private void orderScreen() {
        boolean isRunning = true;
        while (isRunning) {
            clearScreen();
            System.out.println("Welcome, " + currentOrder.getCustomer());
            int response = askUserInt("""
                    ORDER MENU
                    
                    1) ADD DONBURI
                    2) ADD DRINK
                    3) ADD SOUP
                    4) CHECKOUT
                    
                    """);

            switch (response) {
                case 1:
                    processCreateDonburiRequest();
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
                    pause(2000);

            }
        }
    }

    public void processCreateDonburiRequest() {
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
                    return;


                case "m":
                    donburiBuilder(DonburiSize.MEDIUM);
                    return;

                case "l":
                    donburiBuilder(DonburiSize.LARGE);
                    return;

                case "x":
                    isRunning = false;
                    System.out.println("EXITING BACK TO ORDER MENU");
                    break;

                default:
                    System.out.println("INVALID SELECTION, PLEASE TRY AGAIN");
                    scanner.nextLine(); // clear the buffer
            }
        }
    }

    private void donburiBuilder(DonburiSize size) {
        DonburiType donburiType = promptForDonburiType();
        Donburi currentDonburi = new Donburi(donburiType, size);
        currentOrder.addItem(currentDonburi);

        boolean isRunning = true;
        Topping[] selectedToppings = new Topping[ToppingItem.values().length];
        while (isRunning) {
            clearScreen();

            displaySelectedToppings(selectedToppings);
            String responseStr = askUserStr("Please select a topping to add/remove: ");

            if (responseStr.equalsIgnoreCase("C")) {
                System.out.println("Confirming Donburi...");
                pause(2000);
                processAddDonburiToOrderRequest(currentDonburi);
                continue;
            }

            if (responseStr.equalsIgnoreCase("X")) {
                System.out.println("Cancelling Donburi Creation...");
                return;
            }

            int indexOfTopping;
            try {
                indexOfTopping = Integer.parseInt(responseStr) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: please enter a valid number, C, or X.");
                pause(2000); // give user time to read error before it automatically clears at the start of the loop
                continue;
            }

            if (indexOfTopping < 0 || indexOfTopping >= selectedToppings.length) {
                System.out.println("Invalid number: please choose a topping number from the menu.");
                pause(2000);
                continue;
            }

            if (selectedToppings[indexOfTopping] != null) {
                Topping existingTopping = selectedToppings[indexOfTopping];
                currentDonburi.removeTopping(existingTopping);
                selectedToppings[indexOfTopping] = null;
            }
            else {
                ToppingItem toppingItem = ToppingItem.values()[indexOfTopping];
                Topping newTopping = new Topping(toppingItem.getDisplayName(), toppingItem.getType());
                selectedToppings[indexOfTopping] = newTopping;
                currentDonburi.addTopping(newTopping);
            }

        }
    }


    private void promptForDonburiToppings(Donburi currentDonburi) {

    }

    private void processAddDonburiToOrderRequest(Donburi currentDonburi) {
        currentOrder.addItem(currentDonburi);
    }

    private static void displaySelectedToppings(Topping[] selectedToppings) {
        int displayIndex = 1;
        // loop through ToppingType values to get headers above each list of topping categories
        for (ToppingType type : ToppingType.values()) {
            System.out.println(type.name() + ":");

            // for each topping type, list toppings below
            for (ToppingItem toppingItem : ToppingItem.values()) {
                if (toppingItem.getType() == type) {
                    System.out.printf("[%s] %2d) %s%n",
                            (selectedToppings[displayIndex - 1] != null) ? "X" : " ",
                            displayIndex,
                            toppingItem.getDisplayName()
                    );
                    displayIndex++;
                }
            }
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
                    pause(2000);
                }
            }
        }
        return donburiType;
    }


    public String askUserStr(String question) {
        while (true) {
            System.out.println(question);
            try {
                return scanner.nextLine().trim();
            } catch (Exception e) {
                System.out.println("Invalid input!");
                pause(2000);
            }
        }
    }

    public int askUserInt(String question) {
        while (true) {
            System.out.println(question);
            try {
                int response = scanner.nextInt();
                scanner.nextLine();
                return response;
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a number!");
                scanner.nextLine();
                pause(2000);
            }
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

    private void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.printf("Could not pause application for %d seconds", time);
            e.printStackTrace();
        }
    }


    // private void clearScanner() {
    //     while (scanner.hasNext()) {
    //         scanner.nextLine();
    //     }
    // }


}
