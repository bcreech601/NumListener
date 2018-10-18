package com.brad;

public class DedupeStrategy {

    private final boolean[] duplicateArray = new boolean[999999999 + 1];

    public DedupeStrategy(){
        for ( Integer i = 0 ; i < 999999 + 1; ++ i )
            duplicateArray[i] =false;
    }

    public boolean isDuplicate(String key){
        return duplicateArray[Integer.parseInt(key)];
    }

    public void addKey(String key){
        duplicateArray[Integer.parseInt(key)] = true;
    }
}
