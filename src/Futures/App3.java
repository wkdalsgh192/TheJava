package Futures;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };
/*
        Future<String> submit = executorService.submit(hello);
        System.out.println(submit.isDone());
        System.out.println("Started");

        submit.get();

        System.out.println(submit.isDone());
        System.out.println("End!!");
        executorService.shutdown();
        */
/*

        Future<String> submit = executorService.submit(hello);
        System.out.println(submit.isDone());
        System.out.println("Started");

        submit.cancel(false);

        System.out.println(submit.isDone());
        System.out.println("End!!");
        executorService.shutdown();
*/

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> minho = () -> {
            Thread.sleep(1000L);
            return "Minho";
        };

        List<Future<String>> futureList = executorService.invokeAll(Arrays.asList(hello,java,minho));

        futureList.stream().forEach((i) -> {
            try {
                System.out.println(i.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }
}
