package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Garage;
import com.garagesale.domain.Order;
import com.garagesale.exceptions.ProductAlreadyInCartException;
import com.garagesale.domain.PurchaseReceipt;

import java.util.Scanner;

public class Services {


    public static void menuUI(Order order, Garage garage){
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;

        while(keepGoing) {
            System.out.println("Please choose an option: \n1. Asset list\n2. My cart\n3. Proceed to checkout\n4. Exit \n");
            int option = scanner.nextInt();
            System.out.println(" ");
            switch (option) {
                case 1:
                    viewAssetsMenu(order, garage);
                    break;
                case 2:
                    viewMyCart(order);
                    break;
                case 3:
                    checkoutMenu(order);
                    break;
                case 4:
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Please choose an existing option!");
            }
        }

    }

    static void viewAssetsMenu(Order order, Garage garage){
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
            System.out.println("Please choose an option: \n1. Add an asset to your cart\n2. Go back \n");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    buyListMenu(order, garage);
                    break;
                case 2:
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Please choose an existing option!");
            }
        }

    }

    static void buyListMenu(Order order, Garage garage) {
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;

        while(keepGoing) {
            System.out.println("Which one do you wanna add?: ");
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
                        addAssetToCart(order,garage.getAssetList().get(0));
                        keepGoing = false;
                        break;
                    case 2:
                        addAssetToCart(order,garage.getAssetList().get(1));
                        keepGoing = false;
                        break;
                    case 3:
                        addAssetToCart(order,garage.getAssetList().get(2));
                        keepGoing = false;
                        break;
                    case 4:
                        addAssetToCart(order,garage.getAssetList().get(3));
                        keepGoing = false;
                        break;
                    case 5:
                        addAssetToCart(order,garage.getAssetList().get(4));
                        keepGoing = false;
                        break;
                    case 6:
                        addAssetToCart(order,garage.getAssetList().get(5));
                        keepGoing = false;
                        break;
                    default:
                        System.out.println("Please choose an existing option!");
                }
            } catch (ProductAlreadyInCartException exception) {
                System.out.println(exception.getMessage());
                keepGoing = false;
            }
        }

    }

    static void viewMyCart(Order order) {
        if (order.getPurchaseCart().isEmpty()) {
            System.out.println("Cart empty ");
        } else {
            for (Asset temp : order.getPurchaseCart().values()) {
                System.out.println(temp.getAssetName() + " -- " + temp.getPrice() + "USD");
            }
            System.out.println("Total balance: " + order.getPurchaseBalance()+ "\n") ;
        }
    }

    public static void checkoutMenu(Order order){
        boolean keepGoing = true;
        if (order.getPurchaseCart().isEmpty()) {
            System.out.println("Cart empty. Nothing to pay.");
        } else {
            for (Asset temp : order.getPurchaseCart().values()) {
                System.out.println(temp.getAssetName() + " -- " + temp.getPrice() + " USD");
            }
            System.out.println("Total: " + order.getPurchaseBalance() + " USD");
            Scanner scanner = new Scanner(System.in);
            while(keepGoing) {
                System.out.println("1. Continue to pay\n2. Go back\n");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        if(order.getCreditCard().getBalance() >
                                order.getPurchaseBalance())
                        {
                            purchaseCheckout(order);
                        } else {
                            System.out.println("Insufficient balance");
                        }
                        keepGoing = false;
                        break;
                    case 2:
                        keepGoing = false;
                        break;
                    default:
                        System.out.println("Please choose an existing option!\n");
                }
            }
        }

    }

    public static void purchaseCheckout(Order order){
        System.out.println("Please provide a name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("And an valid email adress: ");
        String emailAdress = scanner.nextLine();
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt
                (name, emailAdress, order.getId(), order.getPurchaseCart().values().stream().toList(),
                        order.getPurchaseBalance(), "Credit card " + order.getCreditCard().getCardNumber());
        System.out.println(purchaseReceipt.toString());
    }
    public static void addAssetToCart(Order order, Asset asset) throws ProductAlreadyInCartException{
        if (asset.getQuantity() > 0) {
            if (order.getPurchaseCart().containsKey(asset.getCategory())) {
                throw new ProductAlreadyInCartException("You already have a " + asset.getCategory());
            } else {
                order.getPurchaseCart().put(asset.getCategory(), asset);
                order.setPurchaseBalance(order.getPurchaseBalance()+asset.getPrice());
                System.out.println(asset.getAssetName() + " succesfully added to your cart");
                asset.setQuantity(asset.getQuantity() - 1);
            }
        }
    }

    public static void removeAssetFromCart(Order order, Asset asset) {
        order.getPurchaseCart().remove(asset.getCategory());
        asset.setQuantity(asset.getQuantity() + 1);
    }



}
