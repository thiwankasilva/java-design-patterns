package org.patterns.factory;

import java.util.ArrayList;

public class CalculateAverage extends CalculateTotal {

    private int count;

    public CalculateAverage(ArrayList numbers) {
        super(numbers);
        count = numbers.size();

    }

    @Override
    public int calculate() {
        return super.calculate()/count;
    }
}
