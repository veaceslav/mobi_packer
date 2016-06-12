package com.mobiquintyinc.test;

import com.mobiquintyinc.exception.APIException;
import com.mobiquintyinc.utils.Constants;
import com.mobiquintyinc.utils.Item;
import com.mobiquintyinc.utils.MyParser;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

/**
 * Created by slavik on 6/12/16.
 */
public class ParserTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void testToken(){

        String[] tokens = { "(1,53.38,€45)"};

        try {
            ArrayList<Item> items = MyParser.parseItems(tokens);
            assertEquals(1, items.size());
            Item item = items.get(0);
            assertEquals(1, item.id);
            assertEquals(5338, item.weight);
            assertEquals(45, item.price);
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2Tokens(){

        String[] tokens = { "(1,53.38,€45)","(2,88.62,€98)"};

        try {
            ArrayList<Item> items = MyParser.parseItems(tokens);
            assertEquals(2, items.size());
        } catch (APIException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testOverWeightItem() throws APIException {

        thrown.expect(APIException.class);
        String item = "(1," + Constants.MAX_ITEM_WEIGHT + ".01,€10)";
        String[] tokens = { item };

        ArrayList<Item> items = MyParser.parseItems(tokens);


    }

    @Test
    public void testOverPricedItem() throws APIException {

        thrown.expect(APIException.class);
        String item = "(1,15.38,€" + (Constants.MAX_PRICE +1) + ")";
        String[] tokens = { item };

        ArrayList<Item> items = MyParser.parseItems(tokens);


    }

    @Test
    public void testTooManyItems() throws APIException {

        thrown.expect(APIException.class);
        String[] tokens = new String[Constants.MAX_ITEMS + 1];
        for(int i = 0 ;i < tokens.length; i++)
            tokens[i] = "(1,15.38,€45)";


        ArrayList<Item> items = MyParser.parseItems(tokens);


    }

}
