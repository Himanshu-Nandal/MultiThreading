package com.threading.executorFramework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureEg {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(() -> System.out.println("Hello")); // runnable parameter
        System.out.println(future.get()); // blocking call ( null )
        if(future.isDone()){
            System.out.println("Task is done !");
        }
        executorService.shutdown();

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<String> future = executorService.submit(() -> "Hello"); // callable parameter
//        System.out.println(future.get()); // blocking call
//        if(future.isDone()){
//            System.out.println("Task is done !");
//        }
//        executorService.shutdown();

        // callable is runnable but with a custom return type: "Collable<T> obj .
        // also it can throw exception in the declaration of the method itself,
        // while for runnable we need try-catch to catch th exception"
    }
}

// EXECUTOR SERVICE METHODS AND FUTURE METHODS IN SS