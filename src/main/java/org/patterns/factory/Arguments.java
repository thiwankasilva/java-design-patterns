package org.patterns.factory;

import java.util.ArrayList;
import java.util.List;

public class Arguments {

    private String[] args;

    public Arguments(String[] args) {
        this.args = args;
    }


    public String getOperator()
    {
        return args[0];
    }

    public ArrayList<Integer> getOperands()
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            numbers.add(Integer.parseInt(args[i]));
        }
        return numbers;
    }


}
