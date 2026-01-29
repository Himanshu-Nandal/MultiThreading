package com.threading;

class Counter {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }
// without synchronization
//    public void setCounter() {
//        counter++;
//    }

    //with synchronization
    //1 synchro entire method
//    public synchronized void setCounter() {
//        counter++;
//    }

    //2 only synchronizes the part of method which need it
    public void setCounter() {
        synchronized(this){
            counter++;
        }
    } // part of code accessed by multiple sources is called critical section
}

class Sample2 extends Thread {
    Counter counter;
    Sample2(Counter counter) {
        this.counter = counter;
    }
    public synchronized void incrementCount() {
        counter.setCounter();
    }
    @Override
    public void run() {
        for(int i=0;i<1000;i++) incrementCount();
    }
}

public class synchronization {
    static void main() {
        Counter counter = new Counter();
        // both threads share same resource
        // tht sharing (race condition) results in state change of same resource by all the threads accessing it
        // making faulty progressions. eg. without synchronize it should most rarely show count as 2000
        // which is actually expected all the time
        //synchronization activates a lock on the resource and creates mutual exclusion of tht resource / critical section
        Sample2 t1 = new Sample2(counter);
        Sample2 t2 = new Sample2(counter);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join(); // throws a checked exception
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        System.out.println("actual count: " + counter.getCounter());
    }
}