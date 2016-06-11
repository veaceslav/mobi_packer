package com.mobiquintyinc;

import com.mobiquintyinc.exception.APIException;
import com.mobiquintyinc.pack.Pack;

/**
 * Created by slavik on 6/11/16.
 */
public class PackerApp {

    public static void main(String[] args){

        if(args.length != 0){
            String path = args[0];

            try {
                System.out.println(Pack.pack(path));
            } catch (APIException e) {
                e.printStackTrace();
            }
        }
    }
}
