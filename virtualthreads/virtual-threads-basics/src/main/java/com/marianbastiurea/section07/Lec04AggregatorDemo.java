package com.marianbastiurea.section07;

import com.marianbastiurea.section07.aggregator.AggregatorService;
import com.marianbastiurea.section07.aggregator.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Lec04AggregatorDemo {
    private static final Logger logger = LoggerFactory.getLogger(Lec04AggregatorDemo.class);

    public static void main(String[] args) throws Exception {

        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);
        var futures = IntStream.rangeClosed(1, 50)
                .mapToObj(id -> executor.submit(() -> aggregator.getProductDto(id)))
                .toList();

        var list = futures.stream()
                .map(Lec04AggregatorDemo::toProductDto)
                .toList();
        logger.info("list {}", list);
    }

    private static ProductDTO toProductDto(Future<ProductDTO> future) {
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
