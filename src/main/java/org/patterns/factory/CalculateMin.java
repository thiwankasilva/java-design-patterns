package org.patterns.factory;

import java.util.ArrayList;

public class CalculateMin implements Calc{

    private ArrayList<Integer> numbers;

    public CalculateMin(ArrayList numbers) {
        this.numbers = numbers;
    }

    public int calculate() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            min = min > numbers.get(i) ? numbers.get(i) : min;
        }
        System.out.println("min :" + min);
        return min;
    }
}
