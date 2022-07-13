package com.garagesale.repository;

import com.garagesale.domain.Asset;
import com.garagesale.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AssetRepository{

    public List<Asset> getAssets(){
        return Arrays.asList( new Asset("Mouse Dell MS116", 5.99, new String[]{"-"}, 3, Category.MOUSE,1),
                new Asset("Laptop Lenovo T570 ", 200.0, new String[]{"Low battery"}, 1, Category.LAPTOP,2),
                new Asset("Monitor Dell P2418D", 70.0, new String[]{"1 Dead pixel"}, 1, Category.MONITOR,3),
                new Asset("Keyboard RAZER blackwidow", 70.0, new String[]{"Discharged quickly","asd"}, 1, Category.KEYBOARD,4),
                new Asset("Headphones JABRA Evolve20", 70.0, new String[]{""}, 1, Category.HEADPHONES,5),
                new Asset("Keyboard DELL KM7321", 70.0, new String[]{"Discharged quickly"}, 1, Category.KEYBOARD,6));
    }

}
