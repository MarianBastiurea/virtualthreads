package com.marianbastiurea.section08;

import com.marianbastiurea.section08.aggregator.AggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Lec06AllOff {
    private static final Logger logger = LoggerFactory.getLogger(Lec06AllOff.class);

    public static void main(String[] args)  {

        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        var futures = IntStream.rangeClosed(1, 100)
                .mapToObj(id -> CompletableFuture.supplyAsync(() -> aggregator.getProductDto(id)))
                .toList();

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        var list = futures.stream()
                .map(CompletableFuture::join)
                .toList();
        logger.info("list {}", list);
    }
}
