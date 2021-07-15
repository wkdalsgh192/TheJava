package Futures;

import java.util.Locale;
import java.util.concurrent.*;

public class App4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* future를 이용한 기존 코딩 방식으로 할 수 없는 작업이 많아졌다.
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> future =  executorService.submit(() -> "hello");

         // TODO future를 이용한 작업들

        future.get();
    */
        // CompeletableFuture는 executorService가 필요없다. 기본적으로 ForkJoinPool을 사용하기 때문 하지만 원한다면 언제든지 만들어 줄 수 있다.
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<String> future;
        future = new CompletableFuture<>();
        future.complete("Minho");

        System.out.println(future.get());

        // 위 코드는 다음과 같이 바꿀 수도 있다
        future = CompletableFuture.completedFuture("Minho");
        System.out.println(future.get());

        // 리턴이 없는 작업을 수행할 때
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("Hello "+ Thread.currentThread().getName());
        });
        future2.get(); // 반드시 get()을 해야 시행된다.


        // 리턴타입이 있는 경우
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("HEllo " + Thread.currentThread().getName());
            return "Hello";
        });
        System.out.println(future3.get());

        // NOTE ==> 지금까지는 기존의 Future를 사용하는 것과 기능상 비슷하다.

        // 콜백을 주기
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello "+ Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> { // 리턴값의 유무에 따라 thenApply와 thenAccept 또는 thenRun으로 나뉜다.
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase(Locale.ROOT);
        });

        System.out.println(future4.get());


    }
}
