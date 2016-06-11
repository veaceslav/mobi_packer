package com.mobiquintyinc.utils;

import java.util.ArrayList;

/**
 * Created by slavik on 6/11/16.
 * This class is used to remeber which items are added in the sack
 */
public class Bag{
    public int price;
    public int weight;
    public ArrayList<Integer> items = new ArrayList<>();

    public Bag(){
        price = 0;
        weight = 0;
    }

    public Bag(Bag other){
        this.price = other.price;
        this.weight =other.weight;
        this.items.clear();
        this.items.addAll(other.items);
    }

    public void add(Bag other){
        this.price+= other.price;
        this.weight+=other.weight;
        this.items.addAll(other.items);
    }

    public void addOne(Item item){
        this.price += item.price;
        this.weight += item.weight;
        this.items.add(item.id);
    }
}