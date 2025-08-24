package com.marianbastiurea.section08;

import com.marianbastiurea.section07.Lec03AccessResponseUsingFuture;
import com.marianbastiurea.section07.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Lec04GetProducts {
    private static final Logger logger = LoggerFactory.getLogger(Lec03AccessResponseUsingFuture.class);

    public static void main(String[] args) throws Exception {

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            var product1 = CompletableFuture.supplyAsync(() -> Client.getProduct(1),executor);
            var product2 = CompletableFuture.supplyAsync(() -> Client.getProduct(2),executor);
            var product3 = CompletableFuture.supplyAsync(() -> Client.getProduct(3),executor);
            logger.info("product-1 {}", product1.get());
            logger.info("product-2 {}", product2.get());
            logger.info("product-3 {}", product3.get());

        }
    }
}
