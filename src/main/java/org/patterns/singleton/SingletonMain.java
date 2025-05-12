package org.patterns.singleton;

public class SingletonMain {
    public static void main(String[] args) {
        MethodCallCounter callCounter1 = MethodCallCounter.getInstance();
        callCounter1.count();
        callCounter1.count();
        callCounter1.count();

        MethodCallCounter callCounter2 = MethodCallCounter.getInstance();
        callCounter2.count();
        callCounter2.count();
        callCounter2.count();

        callCounter2.printCount();
        callCounter1.printCount();

    }

}
