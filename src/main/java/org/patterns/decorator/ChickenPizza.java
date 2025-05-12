package org.patterns.decorator;

public class ChickenPizza implements Pizza{



    @Override
    public int getPrice() {
        return 1000;
    }

    @Override
    public String description() {
        return "Chicken Pizza";
    }
}

