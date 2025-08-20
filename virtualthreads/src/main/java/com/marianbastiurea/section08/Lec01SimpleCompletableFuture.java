package com.marianbastiurea.section08;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class Lec01SimpleCompletableFuture {
    private static final Logger logger = LoggerFactory.getLogger(Lec01SimpleCompletableFuture.class);

    public static void main(String[] args) {
        logger.info("main method start");
//        var cf =fastTask();
//        logger.info("value {}", cf.join());

        var cf = slowTask();
        cf.thenAccept(v -> logger.info("value {}", v));
        CommonUtil.sleep(Duration.ofSeconds(2));
        logger.info("main method end");
    }

    private static CompletableFuture<String> fastTask() {
        logger.info("fast method start");
        var cf = new CompletableFuture<String>();
        cf.complete("hi");
        logger.info("fast method end");

        return cf;
    }

    private static CompletableFuture<String> slowTask() {
        logger.info("fast method start");
        var cf = new CompletableFuture<String>();
        Thread.ofVirtual().start(() -> {
            CommonUtil.sleep(Duration.ofSeconds(1));
        });

        cf.complete("hi");
        logger.info("fast method end");

        return cf;
    }
}
