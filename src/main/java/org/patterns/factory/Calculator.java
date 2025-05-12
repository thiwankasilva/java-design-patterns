package org.patterns.factory;

import java.util.ArrayList;

public class Calculator {

    public static void main(String[] args) {

        Arguments arguments = new Arguments(args);
        Calc calculator = null;

        String operator = arguments.getOperator();
        ArrayList<Integer> numbers = arguments.getOperands();


        if (operator.equals("max")) {
            calculator = new CalculateMax(numbers);
        } else if (operator.equals("min")) {
            calculator = new CalculateMin(numbers);
        } else if (operator.equals("total")) {
            calculator = new CalculateTotal(numbers);
        }
        else if (operator.equals("avg")) {
            calculator = new CalculateAverage(numbers);
        }

        int answer = calculator.calculate();
        System.out.println(answer);




    }

}
