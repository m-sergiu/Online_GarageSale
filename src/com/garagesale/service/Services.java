package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Garage;
import com.garagesale.domain.ProductAlreadyInCartException;
import com.garagesale.domain.User;

import java.util.Scanner;

public class Services {
    public Services() {
    }

    public static void menuUI(User user, Garage garage){
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;

        while(keepGoing) {
            System.out.println("Please choose an option: \n1. Asset list\n2. My cart\n3. Proceed to checkout\n4. Exit \n");
            int option = scanner.nextInt();
            System.out.println(" ");
            switch (option) {
                case 1:
                    menuAssetsView(user, garage);
                    break;
                case 2:
                    menuMyCart(user);
                    break;
                case 3:
                case 4:
                    keepGoing = false;
            }
        }

    }

    static void menuAssetsView(User user, Garage garage){
        for(Asset temp: garage.getAssetList()){
            System.out.print(temp.getAssetName() + " -- " + temp.getPrice() + "USD -- Quantity: " +temp.getQuantity() + "; ");
            System.out.print("This asset have these issues: ");
            for(String string: temp.getIssues()){
                System.out.print(string + "; ");
            }
            System.out.println();
        }

        System.out.println();
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;

        while(keepGoing) {
            System.out.println("Please choose an option: \n1. Buy\n2. Go back \n");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    menuBuyList(user, garage);
                    break;
                case 2:
                    keepGoing = false;
            }
        }

    }

    static void menuBuyList(User user, Garage garage) {
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;

        while(keepGoing) {
            System.out.println("Which one do you wanna buy?: ");
            for(int i = 0; i < garage.getAssetList().size(); i++) {
                System.out.print(i + 1 + ". " + (garage.getAssetList().get(i)).getAssetName() + " -- " +
                                                (garage.getAssetList().get(i)).getPrice() + "USD --  quantity: " +
                                                (garage.getAssetList().get(i)).getQuantity() + "; ");
                System.out.print("This asset have these issues: ");
                for(String string: garage.getAssetList().get(i).getIssues()){
                    System.out.print(string + "; ");
                }
                System.out.println();
            }

            int option = scanner.nextInt();

            try {
                switch (option) {
                    case 1:
                        User.addAssetToCart(user, (Asset)garage.getAssetList().get(0));
                        keepGoing = false;
                        break;
                    case 2:
                        User.addAssetToCart(user, (Asset)garage.getAssetList().get(1));
                        keepGoing = false;
                        break;
                    case 3:
                        User.addAssetToCart(user, (Asset)garage.getAssetList().get(2));
                        keepGoing = false;
                        break;
                    case 4:
                        User.addAssetToCart(user, (Asset)garage.getAssetList().get(3));
                        keepGoing = false;
                        break;
                    case 5:
                        User.addAssetToCart(user, (Asset)garage.getAssetList().get(4));
                        keepGoing = false;
                        break;
                    case 6:
                        User.addAssetToCart(user, (Asset)garage.getAssetList().get(5));
                        keepGoing = false;
                }
            } catch (ProductAlreadyInCartException var6) {
                System.out.println("You already have one item of that category");
            }
        }

    }

    static void menuMyCart(User user) {
        if (user.getShoppingCart().isEmpty()) {
            System.out.println("Cart empty ");
        } else {
            for (Asset temp : user.getShoppingCart().values()) {
                System.out.println(temp.getAssetName() + " -- " + temp.getPrice() + "USD");
            }
        }
    }
}
