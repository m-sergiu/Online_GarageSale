package com.garagesale;

import com.garagesale.domain.*;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.ProductAlreadyInCartException;
import com.garagesale.service.Services;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public Main() {
    }

    public static void main(String[] args){
        Asset mouseDellMs116 = new Asset("Mouse Dell MS116", 5.99, new String[]{"-"}, 3, Category.MOUSE);
        Asset laptopLenovoT570 = new Asset("Laptop Lenovo T570 ", 200.0, new String[]{"Low battery"}, 1, Category.LAPTOP);
        Asset monitorDellP2418D = new Asset("Monitor Dell P2418D", 70.0, new String[]{"1 Dead pixel"}, 1, Category.MONITOR);
        Asset keyboardRazerBlackwidow = new Asset("Keyboard RAZER blackwidow", 70.0, new String[]{"Discharged quickly","asd"}, 1, Category.KEYBOARD);
        Asset headphonesJabraEvolve20 = new Asset("Headphones JABRA Evolve20", 70.0, new String[]{""}, 1, Category.HEADPHONES);
        Asset keyboardDellKm7321 = new Asset("Keyboard DELL KM7321", 70.0, new String[]{"Discharged quickly"}, 1, Category.KEYBOARD);


        CreditCard creditCard = new CreditCard("1234567812345678",50000.0, "Sergiu Muntean");
        User sergiu = new User("Sergiu","Sergiu.muntean@endava.com",creditCard);
        Garage garage = new Garage(new ArrayList(List.of(mouseDellMs116, laptopLenovoT570, monitorDellP2418D, keyboardDellKm7321, keyboardRazerBlackwidow, headphonesJabraEvolve20)));
        Purchase purchase = new Purchase(sergiu);


        Services.menuUI(purchase, garage);
    }
}
