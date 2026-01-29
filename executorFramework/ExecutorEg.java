package com.threading.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorEg {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // WITHOUT EXECUTOR
        // Without Thread Array (main thread will start all other threads and move on to execute code ahead while parallelly the running threads will do there work. it will/may effect the work ahead for main. we have to establish order of execution.
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            Thread t = new Thread(() -> {
//                long result = factorial(finalI);
//                System.out.println(result);
//            });
//            t.start();
//        }
        // With Thread Array ( resolves previous problem but here we have to create threads in array, assign them and start them then wait for them to join)
//        Thread[] threads = new Thread[9];
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            threads[i-1] = new Thread(() -> {
//                long result = factorial(finalI);
//                System.out.println(result);
//            });
//            threads[i-1].start();
//        }
//        for (Thread t : threads) {
//            try{
//                t.join();
//            }catch (InterruptedException e){
//                Thread.currentThread().interrupt();
//            }
//        }

        // WITH EXECUTOR (reuses threads since creates a threadpool. It makes, starts and joins threads on its own)
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executor.submit(() -> {
                long result = factorial(finalI);
                System.out.println(result);
            });

        }
        executor.shutdown(); //(we have to manually shut down the executor else it threads will keep running even after completion of tasks)
        // executor.shutdown(); (cant submit tasks to it after shutdown. also the parent thread reading this command does not wait for their joining. ordering problem again)
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS); // resolves ordering problem
            // way to wait indefinately, i.e., till successful termination
//            while(!executor.awaitTermination(10, TimeUnit.MILLISECONDS)){ // repeats the awaitTer. after timeout till successful termination since only then it returns 'true'.
//                System.out.println("waiting... ");
//            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Total time " + (System.currentTimeMillis() - startTime));
    }

    private static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}