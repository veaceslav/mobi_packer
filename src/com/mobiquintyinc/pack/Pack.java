package com.mobiquintyinc.pack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.mobiquintyinc.exception.APIException;
import com.mobiquintyinc.utils.Bag;
import com.mobiquintyinc.utils.Item;
import com.mobiquintyinc.utils.MyParser;

/**
 * Created by slavik on 6/11/16.
 */


public class Pack {

    public static String pack(String filePath) throws APIException {

        StringBuilder result = new StringBuilder();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // process the line.
                    result.append(packInternals(line));
                    result.append("\n");
                }
            }
        } catch (IOException e) {
            throw new APIException("IO Exceptions while reading the file");
        }
        return result.toString();
    }

    public static String packInternals(String data) throws APIException {

        if(!MyParser.matchWholeLine(data)){
            throw new APIException("Line has improper format: " + data);
        }
        String[] tokens = data.split(" ");
        int maxWeight = Integer.parseInt(tokens[0]);
        if(maxWeight > 100){
            throw new APIException("Max Weight is bigger than 100: Actual value: " + maxWeight);
        }
        ArrayList<Item> items = MyParser.parseItems(Arrays.copyOfRange(tokens, 2, tokens.length));

        Bag b = knapsackSolver(items, maxWeight*100);

        return  outputFormat(b);
    }



    public static String outputFormat(Bag b){

        if(b.items.isEmpty()){
            return "-";
        }

        StringBuilder strBuild = new StringBuilder();

        for(Integer id : b.items){
            strBuild.append(id).append(",");
        }

        strBuild.setLength(strBuild.length() -1);

        return strBuild.toString();
    }

    // A utility function that returns maximum of two bags
    static int max(int a, int b) { return (a > b)? a : b; }

    static Bag max(Bag a, Bag b){
        if(a.price != b.price){
            return (a.price > b.price) ? a :b;
        } else {
            return (a.weight < b.weight) ? a :b; // Otherwise return the lightest bag
        }
    }

    public static Bag knapsackSolver(ArrayList<Item> items, int maxWeight){

                int i, w;
                Bag K[][] = new Bag[items.size()+1][maxWeight+1];

                // Build table K[][] in bottom up manner
                for (i = 0; i <= items.size(); i++)
                {
                    for (w = 0; w <= maxWeight; w++)
                    {
                        if (i==0 || w==0) {
                            K[i][w] = new Bag();
                        } else if (items.get(i-1).weight <= w) {
                            Bag tmp = new Bag(K[i - 1][w - (items.get(i - 1).weight)]);
                            tmp.addOne(items.get(i-1));
                            K[i][w] = max(tmp, K[i - 1][w]);
                        } else {
                            K[i][w] = new Bag(K[i - 1][w]);
                        }
                    }
                }
                return K[items.size()][maxWeight];
    }

}
