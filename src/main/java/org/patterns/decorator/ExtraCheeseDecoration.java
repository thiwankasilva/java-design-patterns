package org.patterns.decorator;

public class ExtraCheeseDecoration extends ExtraDecorator {

    public ExtraCheeseDecoration(Pizza pizza) {
        super(pizza);
    }

    @Override
    public int getPrice() {
        return pizza.getPrice()+200;
    }

    @Override
    public String description() {
        return pizza.description()+ "  Cheese Added";
    }
}
