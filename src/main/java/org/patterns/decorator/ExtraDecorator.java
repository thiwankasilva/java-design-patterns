package org.patterns.decorator;

public abstract class ExtraDecorator implements Pizza{

    protected final Pizza pizza;
    public ExtraDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}
