package com.marianbastiurea.section08;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Lec08ThenCombine {
    private static final Logger logger = LoggerFactory.getLogger(Lec08ThenCombine.class);

    record Airfare(String airline, int amount) {
    }

    public static void main(String[] args) {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var cf1 = getDeltaFare(executor);
            var cf2 = getTaromFare(executor);
            var bestDeal = cf1.thenCombine(cf2, (a, b) -> a.amount <= b.amount ? a : b)
                    .thenApply(af -> new Airfare(af.airline(), (int) (af.amount() * 0.9)))
                    .join();

            logger.info("airfare-{}", bestDeal);
        }
    }

    private static CompletableFuture<Airfare> getDeltaFare(ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtil.sleep(Duration.ofMillis(random));
            logger.info("Delta {}", random);
            return new Airfare("Delta", random);
        }, executor);

    }

    private static CompletableFuture<Airfare> getTaromFare(ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtil.sleep(Duration.ofMillis(random));
            logger.info("Tarom {}", random);
            return new Airfare("Tarom", random);
        }, executor);

    }

}
