package org.patterns.factory;

import java.util.ArrayList;

public class CalculateTotal implements Calc{

    private ArrayList<Integer> numbers;

    public CalculateTotal(ArrayList numbers) {
        this.numbers = numbers;
    }

    @Override
    public int calculate() {
        int total = 0;
        for (int i = 0; i < numbers.size(); i++) {
            total = total + numbers.get(i);
        }
        return total;
    }
}
