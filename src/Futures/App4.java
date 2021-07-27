package Futures;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class App4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // future를 이용한 기존 코딩 방식으로 할 수 없는 작업이 많아졌다.
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> future0 =  executorService.submit(() -> "hello");

         // TODO future를 이용한 작업들

        future0.get();

        // CompeletableFuture는 executorService가 필요없다. 기본적으로 ForkJoinPool을 사용하기 때문 하지만 원한다면 언제든지 만들어 줄 수 있다.
        // ExecutorService executorService = Executors.newFixedThreadPool(4);
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


        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = getWorld(hello.get());

        // when two or more futures are dependent each other
        CompletableFuture<String> future5 = hello.thenCompose(App4::getWorld);
        System.out.println(future5.get());

        // when they are independent
        CompletableFuture<String> future6 = hello.thenCombine(world, (h, w) -> h + " " + w);
        System.out.println(future6.get());

        // execute a callback after the given futures are completed - why void?
        CompletableFuture<Void> future7 = CompletableFuture.allOf(hello, world)
                .thenAccept(System.out::println);

        future7.get();

        List<CompletableFuture> futures = Arrays.asList(hello, world);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        CompletableFuture<List<Object>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
                );

        results.get().forEach(System.out::println);


        // Handling Exception
        CompletableFuture<String> exception0 = CompletableFuture.supplyAsync(() -> {
            if (true) throw new IllegalArgumentException();

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello!";
        }).exceptionally(ex -> {
            return "Error!";
        });

        System.out.println(exception0.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    };
}
