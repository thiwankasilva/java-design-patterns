package org.patterns.abstractFactory;

public abstract class AbstractFactory {

    public abstract Button createButton();
    public abstract TextBox createTextBox();
    public abstract Dropdown createDropDown();

}
