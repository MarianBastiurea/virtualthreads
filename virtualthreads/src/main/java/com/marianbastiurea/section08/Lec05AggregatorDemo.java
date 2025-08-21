package com.marianbastiurea.section08;


import com.marianbastiurea.section08.aggregator.AggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

public class Lec05AggregatorDemo {
    private static final Logger logger = LoggerFactory.getLogger(Lec05AggregatorDemo.class);

    public static void main(String[] args) throws Exception {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);
        logger.info("product info {}", aggregator.getProductDto(500));
    }
}

