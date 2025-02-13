package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureExampleTest {

    CompletableFutureExample completableFutureExample = new CompletableFutureExample();
    HelloWorldService helloWorldService = new HelloWorldService();

    @Test
    void asyncFunctionWithCompletableFuture_test() {
        CompletableFuture<String> completableFutureRes = completableFutureExample.asyncFunctionWithCompletableFuture();

        completableFutureRes.thenAccept(str->{
            assertEquals("hello world", str);
        }).join();

    }

    @Test
    void twoAsyncFunctionsWithCompletableFuture_test() {

        String twoAsyncRes = completableFutureExample.twoAsyncFunctionsWithCompletableFuture();

        assertEquals("hello world!", twoAsyncRes);
    }

    @Test
    void multipleAsyncFunctionsWithCompletableFuture_test() {

        String multipleAsyncRes = completableFutureExample.multipleAsyncFunctionsWithCompletableFuture();

        assertEquals("hello world! 3rd completable future!", multipleAsyncRes);
    }

    @Test
    void multipleCompletableFuturesComposition_test() {
        startTimer();
        CompletableFuture<String> completableComposeRes = completableFutureExample.multipleCompletableFuturesComposition();
        timeTaken();
        assertEquals("hello world!, as thenCompose()", completableComposeRes.join());
    }
}