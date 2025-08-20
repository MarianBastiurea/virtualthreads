package com.marianbastiurea.section08;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec02RunAsync {
    private static final Logger logger = LoggerFactory.getLogger(Lec01SimpleCompletableFuture.class);

    public static void main(String[] args) {
        logger.info("main method start");

        runAsync()
                .thenRun(() -> logger.info("task is done"))
                .exceptionally(ex -> {
                    logger.info("error- {}", ex.getMessage());
                    return null;
                });

        var cf = runAsync();
        cf.thenAccept(v -> logger.info("value {}", v));
        CommonUtil.sleep(Duration.ofSeconds(2));
        logger.info("main method end");
    }

    private static CompletableFuture<Void> runAsync() {
        logger.info("run asynchronous  method start");
        var cf = CompletableFuture.runAsync(() -> {
            CommonUtil.sleep(Duration.ofSeconds(1));
            throw new RuntimeException("oops");
        }, Executors.newVirtualThreadPerTaskExecutor());

        logger.info("run asynchronous method end");
        return cf;
    }
}
