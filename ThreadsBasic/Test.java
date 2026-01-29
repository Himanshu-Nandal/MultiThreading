package com.threading;

// 2 ways to execute threads
// way 1 extend Thread class
class ExtendThread extends Thread {
    public ExtendThread(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (; ; ) {
            System.out.println("thread name: " + Thread.currentThread().getName() + " priority: " + Thread.currentThread().getPriority());
        }
    }
}
// way 2 implement Runnable class
class ImplementRunnable implements Runnable{
    @Override
    public void run() {
        for (; ; ) {
            System.out.println("thread name: " + Thread.currentThread().getName() + " priority: " + Thread.currentThread().getPriority());
        }
    }
}
public class Test {

    public static void main(String[] args) {
        // method for way1
        ExtendThread thread1 = new ExtendThread("1"); // NEW State
        thread1.start(); //RUNNABLE State - waiting for CPU time

        // method for way2
        ImplementRunnable obj = new ImplementRunnable();
        Thread thread2 = new Thread(obj); // NEW State
        thread2.start(); //RUNNABLE State

        // execution order of both threads is random and keeps switching between both


    }
}

/*Thread Lifecycle
The lifecycle of a thread in Java consists of several states, which a thread can move through during its execution.

New: A thread is in this state when it is created but not yet started.
Runnable: After the start method is called, the thread becomes runnable. It’s ready to run and is waiting for CPU time.
Running: The thread is in this state when it is executing.
Blocked/Waiting: A thread is in this state when it is waiting for a resource or for another thread to perform an action.
Terminated: A thread is in this state when it has finished executing. // "t.join()" is called in the parent thread class. it waits for 't' to terminate and then joins the 't' thread to its parent thread, i.e., the thread that created the reference to 't'. eg.main is parent of thread1 and thread2
// java shows state of a thread with ".getState()" // running and runnable states are same in java cauz thread either running or just got ready to run
// use of like ".sleep(duration)" puts thread in TIMMED_WAIT State-> state java returns
*/

