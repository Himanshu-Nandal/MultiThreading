package com.threading.executorFramework;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureEg {
    static void main() {
        /* Introduced in Java8, to handle asynchronous programming.

         */
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> { // SUPPLIER OPS
            try {
                Thread.sleep(5000);
                System.out.println("Worker");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return "ok";
        }); // Can pass Executor as another param to use only its threads

        // completableFuture is a daemon thread by default. if we want to wait then we can use get() and the thread will wait
        System.out.println("main"); // this won't wait but the second one will wait

        String s = null;
        try {
            s = completableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s);
        System.out.println("Main");
    }
}
// .get()
// .join()
// .thenApply()
// .orTimeout()