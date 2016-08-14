package com.vivienlk.wardrobeinventory.database;

/**
 * Created by Vivien on 8/14/2016.
 */
public class WardrobeDbSchema {
    public static final class WardrobeTable {
        public static final String NAME = "wardrobe";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String ITEM = "item";
            public static final String DATE = "date";
            public static final String COLORS = "colors";
            public static final String TEXTURES = "textures";
            public static final String OCCASIONS = "occasions";
            public static final String SEASONS = "seasons";
            public static final String FIT = "fit";
            public static final String LENGTH = "length";
            public static final String PRICE = "price";
            public static final String BRAND = "brand";
        }
    }
}
