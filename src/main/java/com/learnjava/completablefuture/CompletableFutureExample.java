package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.delay;

public class CompletableFutureExample {
    private static final HelloWorldService helloWorldService = new HelloWorldService();

    public static void main(String[] args) {
        CompletableFuture
                .supplyAsync(helloWorldService::helloWorld)
//                apply response transformation
                .thenApply(result -> "Hello " + result.toUpperCase())
//               consume final result.
                .thenAccept((response) -> {
                    System.out.println("Response: " + response);
                })
//                apply join() to halt the main thread for consuming result.
                .join();
        System.out.println("Done !!");
    }

    public CompletableFuture<String> asyncFunctionWithCompletableFuture() {
        return CompletableFuture
                .supplyAsync(helloWorldService::helloWorld);
//                apply response transformation
//                .thenApply(result -> "Apply UC: " + result.toUpperCase())
//               consume final result.
//                .thenAccept((response) -> {
//                    System.out.println("Response: " + response);
//                })
//                apply join() to halt the main thread for consuming result.
//                .join().toString();
    }

    public String twoAsyncFunctionsWithCompletableFuture() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(helloWorldService::world);
        return future1
                .thenCombine(future2, (f1, f2) -> f1 + f2)
                .join();
    }

//    thenCombine: for multiple Async/CompletableFuture calls results/responses combination
    public String multipleAsyncFunctionsWithCompletableFuture() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(helloWorldService::hello);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(helloWorldService::world);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return " 3rd completable future!";
        });
        return future1
                .thenCombine(future2, (f1, f2) -> f1 + f2)
                .thenCombine(future3, (prev, f3) -> prev + f3)
                .join();
    }

//    thenCompose: to work wit combination of two Completable Future functions
    public CompletableFuture<String> multipleCompletableFuturesComposition() {
        return CompletableFuture.supplyAsync(helloWorldService::hello).thenCompose(helloWorldService::worldFuture);
    }


}
