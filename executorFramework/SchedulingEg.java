package com.threading.executorFramework;

import java.util.concurrent.*;

public class SchedulingEg {
    static void main() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // Normal schedule (schedules after a delay but cant introduce delays between tasks to schedule after initial delay
        scheduler.schedule(
                () -> System.out.println("task executed every 5 seconds"),
                5,TimeUnit.SECONDS
        );
        scheduler.shutdown();
        /*
        scheduled helps to schedule the execution of the threads
        2 tasks passed to the scheduler
         */
        scheduler.scheduleAtFixedRate(
                () -> System.out.println("task executed every 5 seconds"),
                5,5,TimeUnit.SECONDS
        );
        scheduler.schedule(() -> { // we have to schedule the shutdown task for a scheduleAtFixedRate, since normal .shutdown wont wait for the entire time period to elapse
            System.out.println("initiating shutdown!!");
            scheduler.shutdown();
        }, 20, TimeUnit.SECONDS);

    }
}

// Difference between Executors.newCachedThreadPool() and Executors.newFixedThreadPool()
/* newCachedThreadPool --> Dynamically adjusts pool size. creates threads according to
need and terminates them after 60 seconds of inactivity. Used for variable load and short
lived tasks. works the same way as newFixedThreadPool*/