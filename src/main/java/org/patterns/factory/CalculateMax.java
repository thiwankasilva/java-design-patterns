package org.patterns.factory;

import java.util.ArrayList;

public class CalculateMax implements Calc {

    private ArrayList<Integer> numbers;

    public CalculateMax(ArrayList numbers) {
        this.numbers = numbers;
    }
    public int calculate() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            max = max < numbers.get(i) ? numbers.get(i) : max;
        }
        System.out.println("Max : " + max);
        return max;
    }
}
