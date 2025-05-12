package org.patterns.abstractFactory;

public class ThemeFactory {

    public static AbstractFactory create(String type)
    {
        if(type.equals("Linux"))
        {
            return new WindowsFactory();
        }
        else if(type.equals("Windows"))
        {
            return new LinuxFactory();
        }
        else
        {
            throw new UnsupportedOperationException("Only Linux and windows supported");
        }
    }
}
