package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.models.enums.*;
import com.pluralsight.util.ReceiptWriter;

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
                    processCheckOutRequest();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid input, try again!");
                    pause(2000);
            }
        }
    }

    private void processCheckOutRequest() {
        if (!currentOrder.getAllItems().isEmpty()) {
            String receipt = generateOrderReceiptText();
            ReceiptWriter writer = new ReceiptWriter();
            writer.saveReceiptToFile(receipt);
        }
        else {
            System.out.println("You must order an item before checking out");
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
        DrinkName drinkName = askForDrinkName();
        DrinkSize drinkSize = askForDrinkSize();
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
        System.out.println(generateOrderReceiptText());
    }

    private String generateOrderReceiptText() {
        StringBuilder sb = new StringBuilder();

        if (currentOrder.getAllItems().isEmpty()) {
            sb.append("Your order is currently empty... Order some food!\n");
            return sb.toString();
        }

        if (!currentOrder.getDonburiItems().isEmpty()) {
            sb.append("\tDonburis\n");
            for (Donburi donburi : currentOrder.getDonburiItems()) {
                sb.append("\t\t")
                        .append(donburi.getType().getDisplayName())
                        .append("(").append(donburi.getSize()).append(")")
                        .append("(").append(money(donburi.getSize().getBasePrice())).append(")")
                        .append("\n\t\t")
                        .append("Toasted: ")
                        .append(donburi.isToasted())
                        .append("\n");


                if (!donburi.getListOfPremiumToppings().isEmpty()) {
                    sb.append("\t\t\tPremium Toppings:\n");
                    List<Topping> premiums = donburi.getListOfPremiumToppings();
                    for (int i = 0; i < premiums.size(); i++) {
                        Topping premium = premiums.get(i);
                        if (i > 0) {
                            sb.append("\t\t\t\t- (+").append(money(donburi.getSize().getExtraPrem())).append(") ").append(premium.getName()).append("\n");
                        } else {
                            sb.append("\t\t\t\t- (+").append(money(donburi.getSize().getFirstPrem())).append(") ").append(premium.getName()).append("\n");
                        }
                    }
                }

                if (!donburi.getListOfAromaOils().isEmpty()) {
                    sb.append("\t\t\tAroma Oils:\n");
                    List<Topping> oils = donburi.getListOfAromaOils();
                    for (int i = 0; i < oils.size(); i++) {
                        Topping oil = oils.get(i);
                        if (i > 0) {
                            sb.append("\t\t\t\t- (+").append(money(donburi.getSize().getExtraOil())).append(") ").append(oil.getName()).append("\n");
                        } else {
                            sb.append("\t\t\t\t- (+").append(money(donburi.getSize().getFirstOil())).append(") ").append(oil.getName()).append("\n");
                        }
                    }
                }

                if (!donburi.getListOfRegularToppings().isEmpty()) {
                    sb.append("\t\t\tRegular Toppings:\n");
                    for (Topping topping : donburi.getListOfRegularToppings()) {
                        sb.append("\t\t\t\t- ").append(topping.getName()).append("\n");
                    }
                }

                if (!donburi.getListOfSauces().isEmpty()) {
                    sb.append("\t\t\tSauce:\n");
                    for (Topping topping : donburi.getListOfSauces()) {
                        sb.append("\t\t\t\t- ").append(topping.getName()).append("\n");
                    }
                }

                if (!donburi.getListOfSides().isEmpty()) {
                    sb.append("\t\t\tSide:\n");
                    for (Topping topping : donburi.getListOfSides()) {
                        sb.append("\t\t\t\t- ").append(topping.getName()).append("\n");
                    }
                }

                sb.append("\t\t\t\t\t\tItem Total: ").append(money(donburi.getPrice())).append("\n");
            }
        }

        if (!currentOrder.getDrinkItems().isEmpty()) {
            sb.append("\tDrinks:\n");
            for (Drink drink : currentOrder.getDrinkItems()) {
                sb.append("\t\t").append(drink.getName()).append("(").append(drink.getSize().name()).append(")\n");
                sb.append("\t\t\t\t\t\tItem Total: ").append(money(drink.getPrice())).append("\n");
            }
        }

        if (!currentOrder.getSoupItems().isEmpty()) {
            sb.append("\tSoup:\n");
            for (Soup soup : currentOrder.getSoupItems()) {
                sb.append("\t\t").append(soup.getName()).append(":\n");
                sb.append("\t\t\t\t\t\tItem Total: ").append(money(soup.getPrice())).append("\n");
            }
        }

        sb.append("Order Total: ").append(money(currentOrder.getOrderTotal())).append("\n");

        return sb.toString();
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
                boolean toasted = (askUserInt("""
                        Do you want your donburi toasted?
                        
                        1) yes
                        2) no
                        3) what
                        
                        """) == 1);
                currentDonburi.setToasted(toasted);
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


    private String money(double value) {
        return String.format("$%.2f", value);
    }



}
