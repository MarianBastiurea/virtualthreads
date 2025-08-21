package com.marianbastiurea.section08;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Lec07AnyOf {
    private static final Logger logger = LoggerFactory.getLogger(Lec07AnyOf.class);

    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var cf1 = getDeltaFare(executor);
            var cf2 = getTaromFare(executor);
            logger.info("airfare-{}", CompletableFuture.anyOf(cf1, cf2).join());
        }
    }

    private static CompletableFuture<String> getDeltaFare(ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtil.sleep(Duration.ofMillis(random));
            return "delta-{}" + random;
        }, executor);

    }

    private static CompletableFuture<String> getTaromFare(ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtil.sleep(Duration.ofMillis(random));
            return "delta-{}" + random;
        }, executor);

    }

}
