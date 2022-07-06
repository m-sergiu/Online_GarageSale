package com.garagesale;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Category;
import com.garagesale.domain.Garage;
import com.garagesale.domain.ProductAlreadyInCartException;
import com.garagesale.domain.User;
import com.garagesale.service.Services;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws ProductAlreadyInCartException {
        Asset mouseDellMs116 = new Asset("Mouse Dell MS116", 5.99, new String[]{"-"}, 3, Category.Mouse);
        Asset laptopLenovoT570 = new Asset("Laptop Lenovo T570 ", 200.0, new String[]{"Low battery"}, 1, Category.Laptop);
        Asset monitorDellP2418D = new Asset("Monitor Dell P2418D", 70.0, new String[]{"1 Dead pixel"}, 1, Category.Monitor);
        Asset keyboardRazerBlackwidow = new Asset("Keyboard RAZER blackwidow", 70.0, new String[]{"Discharged quickly","asd"}, 1, Category.Keyboard);
        Asset headphonesJabraEvolve20 = new Asset("Headphones JABRA Evolve20", 70.0, new String[]{""}, 1, Category.HeadPhones);
        Asset keyboardDellKm7321 = new Asset("Keyboard DELL KM7321", 70.0, new String[]{"Discharged quickly"}, 1, Category.Keyboard);
        User marian = new User("Marian");
        Garage garage = new Garage(new ArrayList(List.of(mouseDellMs116, laptopLenovoT570, monitorDellP2418D, keyboardDellKm7321, keyboardRazerBlackwidow, headphonesJabraEvolve20)));
        Services.menuUI(marian, garage);
    }
}
