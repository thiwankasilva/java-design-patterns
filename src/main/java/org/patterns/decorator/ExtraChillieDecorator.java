package org.patterns.decorator;

public class ExtraChillieDecorator extends ExtraDecorator{

    public ExtraChillieDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public int getPrice() {
        return pizza.getPrice()+100;
    }

    @Override
    public String description() {
        return pizza.description()+" Chillie Added";
    }
}
