package org.patterns.singleton;

import java.util.concurrent.locks.ReentrantLock;

public class MethodCallCounter {
    private int callCount;

    private static MethodCallCounter instance;

    private static ReentrantLock lock = new ReentrantLock();

    public static MethodCallCounter getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new MethodCallCounter();
            }
            lock.unlock();
        }
        return instance;
    }
    private MethodCallCounter() {
    }

    public void count()
    {
        callCount++;
    }
    public void printCount()
    {
        System.out.println(callCount);
    }
}
