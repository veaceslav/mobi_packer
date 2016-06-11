package com.mobiquintyinc.utils;

import com.mobiquintyinc.exception.APIException;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.regex.Pattern;

/**
 * Created by slavik on 6/11/16.
 */
public class MyParser {

    public static ArrayList<Item> parseItems(String[] tokens) throws APIException {

        ArrayList<Item> items = new ArrayList<>();
        for(int i = 0; i < tokens.length; i++){

            if(!matchToken(tokens[i])){
                System.out.print(tokens[i]);
                throw new APIException("Item has improper format");
            }

            String[] processed = tokens[i].replaceAll("\\(", "")
                    .replaceAll("\\)","")
                    .replaceAll("€", "")
                    .split(",");
            int id = Integer.parseInt(processed[0]);
            double weight = Double.parseDouble(processed[1]);
            int    price  = Integer.parseInt(processed[2]);
            if(weight > 100){
                throw new APIException("Item weight is bigger than 100");
            }

            if(price > 100){
                throw  new APIException("Item price is higher than 100");
            }

            items.add(new Item(price,(int)(weight*100),id));
        }

        return items;
    }

    public static boolean matchToken(String  token){
        Pattern p = Pattern.compile("^\\([\\d]+,[\\d]+\\.[\\d]+,€[\\d]+\\)$");
        return p.matcher(token).matches();
    }

    public static boolean matchWholeLine(String  line){
        Pattern p = Pattern.compile("^[\\d]+\\ :( \\([\\d]+,[\\d]+\\.[\\d]+,€[\\d]+\\))*");
        return p.matcher(line).matches();
    }
}
