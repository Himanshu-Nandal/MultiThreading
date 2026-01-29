package com.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer method");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        lock.lock();
        try {
            System.out.println("Inner method");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();
        example.outerMethod();
    }
}

// reentrant lock prevents deadlocks, means that it stores which thread locked it. hence, if the same thread tries to lock this lock again n again then it can proceed
// it keeps count of no. of times the thread has locked the critical section
// the thread's execution won't be suspended
// this is the adv of ReentrantLock() over synchronized keyword
// IMP: reentrant lock matches every lock() with every unlock(). eg. if i call lock 2 times, then i need to call to unlock again 2 times explicit to resume the execution for other threads
