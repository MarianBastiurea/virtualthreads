package com.marianbastiurea.section08;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec03SupplyAsync {
    private static final Logger logger = LoggerFactory.getLogger(Lec01SimpleCompletableFuture.class);

    public static void main(String[] args) {
        logger.info("main method start");

        var cf = slowTask();
        cf.thenAccept(v -> logger.info("value {}", v));
        CommonUtil.sleep(Duration.ofSeconds(2));
        logger.info("main method end");
    }

    private static CompletableFuture<String> slowTask() {
        logger.info("fast method start");
        var cf = CompletableFuture.supplyAsync(() -> {
            CommonUtil.sleep(Duration.ofSeconds(1));
            return "hi";
        }, Executors.newVirtualThreadPerTaskExecutor());

        logger.info("fast method end");

        return cf;
    }
}
