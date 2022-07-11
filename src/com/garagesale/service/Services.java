package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Garage;
import com.garagesale.domain.Purchase;
import com.garagesale.exceptions.ProductAlreadyInCartException;
import com.garagesale.domain.PurchaseReceipt;

import java.util.Scanner;

public class Services {
    public Services() {
    }

    public static void menuUI(Purchase purchase, Garage garage){
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;

        while(keepGoing) {
            System.out.println("Please choose an option: \n1. Asset list\n2. My cart\n3. Proceed to checkout\n4. Exit \n");
            int option = scanner.nextInt();
            System.out.println(" ");
            switch (option) {
                case 1:
                    viewAssetsMenu(purchase, garage);
                    break;
                case 2:
                    viewMyCart(purchase);
                    break;
                case 3:
                    checkoutMenu(purchase);
                    break;
                case 4:
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Please choose an existing option!");
            }
        }

    }

    static void viewAssetsMenu(Purchase purchase, Garage garage){
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
                    buyListMenu(purchase, garage);
                    break;
                case 2:
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Please choose an existing option!");
            }
        }

    }

    static void buyListMenu(Purchase purchase, Garage garage) {
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
                        addAssetToCart(purchase,garage.getAssetList().get(0));
                        keepGoing = false;
                        break;
                    case 2:
                        addAssetToCart(purchase,garage.getAssetList().get(1));
                        keepGoing = false;
                        break;
                    case 3:
                        addAssetToCart(purchase,garage.getAssetList().get(2));
                        keepGoing = false;
                        break;
                    case 4:
                        addAssetToCart(purchase,garage.getAssetList().get(3));
                        keepGoing = false;
                        break;
                    case 5:
                        addAssetToCart(purchase,garage.getAssetList().get(4));
                        keepGoing = false;
                        break;
                    case 6:
                        addAssetToCart(purchase,garage.getAssetList().get(5));
                        keepGoing = false;
                        break;
                    default:
                        System.out.println("Please choose an existing option!");
                }
            } catch (ProductAlreadyInCartException exception) {
                System.out.println(exception.getMessage());
            }
        }

    }

    static void viewMyCart(Purchase purchase) {
        if (purchase.getPurchaseCart().isEmpty()) {
            System.out.println("Cart empty ");
        } else {
            for (Asset temp : purchase.getPurchaseCart().values()) {
                System.out.println(temp.getAssetName() + " -- " + temp.getPrice() + "USD");
            }
            System.out.println("Total balance: " + purchase.getPurchaseBalance()+ "\n") ;
        }
    }

    public static void checkoutMenu(Purchase purchase){
        boolean keepGoing = true;
        if (purchase.getPurchaseCart().isEmpty()) {
            System.out.println("Cart empty. Nothing to pay.");
        } else {
            for (Asset temp : purchase.getPurchaseCart().values()) {
                System.out.println(temp.getAssetName() + " -- " + temp.getPrice() + " USD");
            }
            System.out.println("Total: " + purchase.getPurchaseBalance() + " USD");
            Scanner scanner = new Scanner(System.in);
            while(keepGoing) {
                System.out.println("1. Continue to pay\n2. Go back\n");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        if(purchase.getCreditCard().getBalance() >
                                purchase.getPurchaseBalance())
                        {
                            purchaseCheckout(purchase);
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

    public static void purchaseCheckout(Purchase purchase){
        System.out.println("Please provide a name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("And an valid email adress: ");
        String emailAdress = scanner.nextLine();
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt
                (name, emailAdress, purchase.getId(), purchase.getPurchaseCart().values().stream().toList(),
                        purchase.getPurchaseBalance(), "Credit card " + purchase.getCreditCard().getCardNumber());
        System.out.println(purchaseReceipt.toString());
    }
    public static void addAssetToCart(Purchase purchase, Asset asset) throws ProductAlreadyInCartException{
        if (asset.getQuantity() > 0) {
            if (purchase.getPurchaseCart().containsKey(asset.getCategory())) {
                throw new ProductAlreadyInCartException("You already have a " + asset.getCategory());
            } else {
                purchase.getPurchaseCart().put(asset.getCategory(), asset);
                purchase.setPurchaseBalance(purchase.getPurchaseBalance()+asset.getPrice());
                System.out.println(asset.getAssetName() + " succesfully added to your cart");
                asset.setQuantity(asset.getQuantity() - 1);
            }
        }
    }

    public static void removeAssetFromCart(Purchase purchase, Asset asset) {
        purchase.getPurchaseCart().remove(asset.getCategory());
        asset.setQuantity(asset.getQuantity() + 1);
    }



}
