package com.brad;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberInputProtocol {

    private final Pattern pattern;

    public NumberInputProtocol(){
        pattern = Pattern.compile("[0-9]{9,9}");
    }

    public boolean isTerminateInput(String input){
        return input.equals("terminate"); // assume case sensitivity
    }

    public boolean isValidInput(String input){
        Matcher m = pattern.matcher(input);
        return input.length() == 9 && m.find();
    }
}
