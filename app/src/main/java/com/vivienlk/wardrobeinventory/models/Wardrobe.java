package com.vivienlk.wardrobeinventory.models;

/**
 * Created by Vivien on 8/25/2016.
 */
public class Wardrobe {
    private static Wardrobe ourInstance = new Wardrobe();

    public static Wardrobe getInstance() {
        return ourInstance;
    }

    private Wardrobe() {
    }

}
