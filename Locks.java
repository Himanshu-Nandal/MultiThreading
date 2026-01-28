package com.threading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

class BankAccount {

    private final Lock lock = new ReentrantLock();

    private int balance = 1200;
    public void withdraw(int amt) {
        try {
            // lock.lock() is like synchronized -> waits indefinite
            if(lock.tryLock(2000, TimeUnit.MILLISECONDS)) { // a thread tries for the given time. if granted then lock is applied else 'else' block executed
                System.out.println("thread: " + Thread.currentThread().getName() + " withdrawing...");
                try {
                    if(balance >= amt) {
                        Thread.sleep(300);
                        balance -= amt;
                        System.out.println(Thread.currentThread().getName() + " thread withdraw operation complete");
                    }
                    else throw new Exception();
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + " balance insufficient!!");
                } finally {
                    lock.unlock();
                }
            }
        } catch (Exception e) { // to catch any exceptions like intruptions etc. thrown in waiting time
            System.out.println(Thread.currentThread().getName()+" another thread is busy");
        } // can also use .isInterrupted() in both catches to inform system about interrupt
    }
}

public class Locks {
    static void main() {
        /*
        two types of locks-
        1. intrinsic: the locks are built by Java like using 'synchronized' keyword
        2. extrinsic: these are advanced locks that help user define the custom behavior of lock
         */

        BankAccount bankAccount = new BankAccount();
        Runnable runnable = () -> {
            try {
                bankAccount.withdraw(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }
}