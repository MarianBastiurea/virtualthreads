package com.marianbastiurea.section07;

import com.marianbastiurea.section07.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

public class Lec03AccessResponseUsingFuture {
    private static final Logger logger = LoggerFactory.getLogger(Lec03AccessResponseUsingFuture.class);

    public static void main(String[] args) throws Exception {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            var product1 = executor.submit(() -> Client.getProduct(1));
            var product2 = executor.submit(() -> Client.getProduct(2));
            var product3 = executor.submit(() -> Client.getProduct(3));
            logger.info("product-1 {}", product1.get());
            logger.info("product-2 {}", product2.get());
            logger.info("product-3 {}", product3.get());

        }
    }

}
