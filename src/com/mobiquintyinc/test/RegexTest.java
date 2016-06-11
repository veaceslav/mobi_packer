package com.mobiquintyinc.test;

import com.mobiquintyinc.utils.MyParser;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by slavik on 6/11/16.
 */
public class RegexTest {

    @Test
    public void testNoElement(){
        String data = "8 : (1,15.3,€34)";

        assertTrue(MyParser.matchWholeLine(data));
    }

    @Test
    public void testOneElement(){
        String data = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48) 8 : (1,15.3,€34)";
        String expected = "4";

        assertFalse(MyParser.matchWholeLine(data));
    }

    @Test
    public void testTwoElementsInTheList(){
        String data = "75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)";

        assertTrue(MyParser.matchWholeLine(data));
    }

    @Test
    public void testTwoElementsInTheList2() {
        String data = "56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)";
        String expected = "8,9";
        assertTrue(MyParser.matchWholeLine(data));
    }

    @Test
    public void testSingleToken(){
        String data = "(1,15.3,€34)";

        assertTrue(MyParser.matchToken(data));
    }

    @Test
    public void testSingleToken2(){
        String data = "(11,15.31,€34)";

        assertTrue(MyParser.matchToken(data));
    }
}
