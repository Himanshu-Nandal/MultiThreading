package com.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLockMethod {
    private final Lock lock = new ReentrantLock(true);

    public void accessResource() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock.");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FairnessLockMethod example = new FairnessLockMethod();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                example.accessResource();
            }
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");
        Thread thread3 = new Thread(task, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

// to process the threads in sequential order, we pass fair var as true when creating reentrant lock
// so lock will bw given in order of requests of threads. also prevents starvation of any thread cauz of race and gives chance to every thread (all unlike in unfairLock )


// READ AND WRITE LOCKING!!
/*
the object created is of ReentrantReadWriteLock(). this allows multiple threads to threads who are reading but not writing
when writing then only one thread is given access

the read lock can be called by calling readLock() and write lock can be called by writeLock()
 */

//synchronization: no fairness
//        blocking
//        no interruptability
//        no read/write locking