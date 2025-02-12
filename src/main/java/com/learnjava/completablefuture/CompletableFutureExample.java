package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        HelloWorldService helloWorldService = new HelloWorldService();

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
}
