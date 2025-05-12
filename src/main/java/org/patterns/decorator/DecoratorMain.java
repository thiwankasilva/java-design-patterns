package org.patterns.decorator;

public class DecoratorMain {
    public static void main(String[] args) {
        Pizza chickenPizza = new ChickenPizza();

        chickenPizza = new ExtraCheeseDecoration(chickenPizza);
        chickenPizza = new ExtraChillieDecorator(chickenPizza);

        System.out.println("Price : "+chickenPizza.getPrice());
        System.out.println("Description : "+chickenPizza.description());
    }
}
