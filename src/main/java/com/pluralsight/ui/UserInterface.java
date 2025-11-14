package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.models.enums.*;

import java.util.List;
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
            displayCurrentOrder();
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
                    processAddDrinkRequest();
                    break;
                case 3:
                    processAddSoupRequest();
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

    private void processAddSoupRequest() {
        SoupName soupName = null;
        boolean isRunning = true;
        while (isRunning) {
            int response = askUserInt("""
                    SOUPS
                    
                    1) Miso Soup
                    2) Seafood Soup
                    
                    Please select a drink to add:
                    
                    """);

            switch (response) {
                case 1:
                    soupName = SoupName.MISO_SOUP;
                    isRunning = false;
                    break;
                case 2:
                    soupName = SoupName.SEAFOOD_SOUP;
                    isRunning = false;
                    break;
                default:
                    System.out.println("Incorrect input. Try again");
                    pause(2000);
            }
        }
        currentOrder.addItem(new Soup(soupName));
    }

    private void processAddDrinkRequest() {
        DrinkSize drinkSize = askForDrinkSize();
        DrinkName drinkName = askForDrinkName();
        Drink newDrink = new Drink(drinkName, drinkSize);
        currentOrder.addItem(newDrink);
    }

    private DrinkName askForDrinkName() {
        DrinkName drinkName = null;
        boolean isRunning = true;
        while (isRunning) {
            int response = askUserInt("""
                    DRINKS
                    
                    1) HIGHBALL
                    2) BEER
                    
                    Please select a drink to add:
                    
                    """);

            switch (response) {
                case 1:
                    drinkName = DrinkName.HIGHBALL;
                    isRunning = false;
                    break;
                case 2:
                    drinkName = DrinkName.BEER;
                    isRunning = false;
                    break;
                default:
                    System.out.println("Incorrect input. Try again");
                    pause(2000);
            }
        }
        return drinkName;
    }

    private DrinkSize askForDrinkSize() {
        boolean isRunning = true;
        DrinkSize size = null;
        while (isRunning) {
            int response = askUserInt("""
                    What size drink do you want?
                    
                    1) Regular
                    2) Large
                    
                    """);
            switch (response) {
                case 1:
                    size = DrinkSize.REGULAR;
                    isRunning = false;
                    break;
                case 2:
                    size = DrinkSize.LARGE;
                    isRunning = false;
                    break;
                default:
                    System.out.println("Incorrect input. Try again");
                    pause(2000);
            }
        }
        return size;
    }


    private void displayCurrentOrder() {
        System.out.println("Current Order:");
        if (currentOrder.getAllItems().isEmpty()) {
            System.out.println("Your order is currently empty... Order some food!");
            return;
        }

        if (!currentOrder.getDonburiItems().isEmpty()) {
            System.out.println("\tDonburi's:");
            for (Donburi donburi : currentOrder.getDonburiItems()) {
                System.out.println("\t" + donburi.getType().getDisplayName() + "( " + donburi.getSize() + " Base Price: " + donburi.getSize().getBasePrice() + ")");

                if (!donburi.getListOfPremiumToppings().isEmpty()) {
                    System.out.println("\t\tPremium Toppings:");
                    List<Topping> premiums = donburi.getListOfPremiumToppings();
                    for (int i = 0; i < premiums.size(); i++) {
                        Topping premium = premiums.get(i);
                        if (i > 0) {
                            System.out.println("\t\t\t- (+" + donburi.getSize().getExtraPrem() + ") " + premium.getName());
                        } else {
                            System.out.println("\t\t\t- (+" + donburi.getSize().getFirstPrem() + ") " + premium.getName());
                        }
                    }
                }

                if (!donburi.getListOfAromaOils().isEmpty()) {
                    System.out.println("\t\tAroma Oils:");
                    List<Topping> oils = donburi.getListOfAromaOils();
                    for (int i = 0; i < oils.size(); i++) {
                        Topping oil = oils.get(i);
                        if (i > 0) {
                            System.out.println("\t\t\t- (+" + donburi.getSize().getExtraOil() + ") " + oil.getName());
                        } else {
                            System.out.println("\t\t\t- (+" + donburi.getSize().getFirstOil() + ") " + oil.getName());
                        }
                    }
                }

                if (!donburi.getListOfRegularToppings().isEmpty()) {
                    System.out.println("\t\tRegular Toppings:");
                    for (Topping topping : donburi.getListOfRegularToppings()) {
                        System.out.println("\t\t\t- " + topping.getName());
                    }
                }

                if (!donburi.getListOfSauces().isEmpty()) {
                    System.out.println("\t\tSauce:");
                    for (Topping topping : donburi.getListOfSauces()) {
                        System.out.println("\t\t\t- " + topping.getName());
                    }
                }

                if (!donburi.getListOfSides().isEmpty()) {
                    System.out.println("\t\tRegular Toppings:");
                    for (Topping topping : donburi.getListOfSides()) {
                        System.out.println("\t\t\t- " + topping.getName());
                    }
                }

                System.out.println("\t\t\t\t\t\tItem Total: " + donburi.getPrice());
            }
        }

        if (!currentOrder.getDrinkItems().isEmpty()) {
            System.out.println("\tDrinks:");
            for (Drink drink : currentOrder.getDrinkItems()) {
                System.out.println("\t\t\t\t\t\t" + drink.getName() + ": " + drink.getPrice());
            }
        }

        if (!currentOrder.getSoupItems().isEmpty()) {
            System.out.println("\tSoup:");
            for (Soup soup : currentOrder.getSoupItems()) {
                System.out.println("\t\t\t\t\t\t" + soup.getName() + ": " + soup.getPrice());
            }
        }

        System.out.println("Order Total: " + currentOrder.getOrderTotal());
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
                    pause(2000);
            }
        }
    }

    private void donburiBuilder(DonburiSize size) {
        DonburiType donburiType = promptForDonburiType();
        Donburi currentDonburi = new Donburi(donburiType, size);

        boolean isRunning = true;
        Topping[] selectedToppings = new Topping[ToppingItem.values().length];
        while (isRunning) {
            clearScreen();

            displaySelectedToppings(selectedToppings);
            String responseStr = askUserStr("""
                    
                    Please input corresponding number to add/remove topping
                    OR
                    C) Confirm Toppings     X) Cancel Donburi Order
                    """);

            if (responseStr.equalsIgnoreCase("C")) {
                System.out.println("Confirming Donburi...");
                pause(2000);
                processAddDonburiToOrderRequest(currentDonburi);
                return;
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
            } else {
                ToppingItem toppingItem = ToppingItem.values()[indexOfTopping];
                Topping newTopping = new Topping(toppingItem, toppingItem.getType());
                selectedToppings[indexOfTopping] = newTopping;
                currentDonburi.addTopping(newTopping);
            }

        }
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
