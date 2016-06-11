package com.mobiquintyinc.test;

import com.mobiquintyinc.pack.Pack;
import com.mobiquintyinc.utils.Bag;
import com.mobiquintyinc.utils.Item;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by slavik on 6/11/16.
 */
public class PackKnapsackTest {


    /**
     * All weights are bigger than maxweight
     */
    @Test
    public void testNoElements(){
        int maxWeight = 8100;
        int weight[] = {9072, 13380, 14315, 13797, 14681, 14877, 18180, 11936, 11676 };
        int price[] = {13,40,10, 16, 36, 79, 45, 79, 64};

        Bag b = Pack.knapsackSolver(makeItems(weight,price), maxWeight);

        assertTrue(b.items.isEmpty());
    }

    /**
     * One weight is smaller than maxWeight
     */
    @Test
    public void testOne(){
        int maxWeight = 5600;
        int weight[] = {9072, 3380, 114315, 113797, 114681, 114877, 118180, 111936, 11676 };
        int price[] = {13,40,10, 16, 36, 79, 45, 79, 64};

        Bag b = Pack.knapsackSolver(makeItems(weight,price), maxWeight);

        assertEquals(1, b.items.size());
        assertTrue(b.items.contains(2));
    }

    /**
     * One weight is smaller than maxWeight
     */
    @Test
    public void testOne2(){
        int maxWeight = 8100;
        int weight[] = {5338, 8862, 7848, 7230, 3018, 4634};
        int price[] = {45, 98, 3, 76, 9, 48};

        Bag b = Pack.knapsackSolver(makeItems(weight,price), maxWeight);


        assertEquals(1, b.items.size());
        assertTrue(b.items.contains(4));
    }


    @Test
    public void testMultiple1(){
        int maxWeight = 5600;
        int weight[] = {9072, 3380, 4315, 3797, 4681, 4877, 8180,1936, 676 };
        int price[] = {13,40,10, 16, 36, 79, 45, 79, 64};

        Bag b = Pack.knapsackSolver(makeItems(weight,price), maxWeight);

        assertEquals(2, b.items.size());
        assertTrue(b.items.contains(8));
        assertTrue(b.items.contains(9));
    }


    @Test
    public void testMultiple2(){


        int maxWeight = 7500;
        int weight[] = {8531, 1455, 398, 2624, 6369, 7625, 6002, 9318, 8995};
        int price[] = {29, 74, 16, 55, 52, 75, 74, 35, 78};

        Bag b = Pack.knapsackSolver(makeItems(weight,price), maxWeight);

        assertEquals(2, b.items.size());
        assertTrue(b.items.contains(2));
        assertTrue(b.items.contains(7));
    }

    ArrayList<Item> makeItems(int weight[], int price[]){
        ArrayList<Item> items= new ArrayList<Item>();

        for(int i = 0; i < price.length; i++){
            items.add(new Item(price[i],weight[i], i+1));
        }
        return items;
    }
}
