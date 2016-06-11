package com.mobiquintyinc.utils;

/**
 * Created by slavik on 6/11/16.
 * this class is container for item id, price and weight
 */
public class Item {
    public int price;
    public int weight;
    public int id;

    public Item(int price, int weight, int id){
        this.price = price;
        this.weight = weight;
        this.id = id;
    }
}
