package com.threading;

class Sample1 extends Thread{
    public Sample1(String name){
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i <6; i++) {
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            System.out.println(i);
        }
    }
}
class DaemonEg extends Thread {
    @Override
    public void run() {
        while(true) {
            System.out.println("hello world!!");
            Thread.yield();
        }
    }
}
public class ThreadMethods {
    static void main() throws Exception {
        // setting the name of the thread // uses super(p) in the constructor of the class ExtendThread
        Sample1 l = new Sample1("bhaskar");
        Sample1 m = new Sample1("abc");
        Sample1 h = new Sample1("xyz");
        System.out.println("child thread name: " + l.getName());

        // setPriority method -> used to set (more like suggest, as it doesn't always follow this priority) the priority of the thread in case there are multiple threads. followed better on single core, since multi care implies parallel exicution
        System.out.println(Thread.currentThread().getName() + " priority: " + Thread.currentThread().getPriority());
        l.setPriority(Thread.MIN_PRIORITY);
        m.setPriority(Thread.NORM_PRIORITY);
        h.setPriority(Thread.MAX_PRIORITY);
        l.start(); // threads have started
        m.start();
        h.start();

        l.join();
        m.join();
        h.join();
        // max priority = 10, min = 1 and normal = 5 (normal = by default)

        // .interrupt() method -> means that the current method's execution should be stopped. if it's wrapped in try catch then catch exception will be executed
        // Thread.yield method -> int the threads class hints/suggests the scheduler that other thread can be given the chance for execution. means that the thread prompts the scheduler
        // to context switch the thread if there are other threads stalled in the ready queue

        // .setDaemon() -> Thread will be marked as Daemon, means that the current thread will run in the background. if the user (eg. parent) threads are terminated then it will also terminate itself
        DaemonEg demo = new DaemonEg();
        demo.setDaemon(true); // method must be called before start()
        demo.start();
        System.out.println("hello world from main!!");

        // .isInterrupted() method used to tell whether a thread is interrupted or not. returns bool value and restores the state of the interrupted thread
        // used to inform system that interrupt occured as just logging the interrupt is not enough, as it will lose its state
        // eg. Thread.currentThread().isInterrupted();

    }
}
