package Futures;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class App2 {

    public static void main(String[] args) {
        /*
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println("Thread "+Thread.currentThread().getName());
            }
        });

        executorService.submit(() -> {
            System.out.println("Thread "+ Thread.currentThread().getName());
        });

        // executor's task should be shut down manually - graceful shutdown
        executorService.shutdown();
       */

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(getRunnable("A"));
        executorService.submit(getRunnable("B"));
        executorService.submit(getRunnable("C"));
        executorService.submit(getRunnable("D"));
        executorService.submit(getRunnable("E"));

        executorService.shutdown();

    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
