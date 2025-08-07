package com.marianbastiurea.section03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CPUTaskDemo {
    public static final int TASK_COUNT = 1;
private static final Logger logger= LoggerFactory.getLogger(CPUTaskDemo.class);
    public static void main(String[] args) {
        demo(Thread.ofPlatform());
    }

    private static void demo(Thread.Builder builder) {
        var latch = new CountDownLatch(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++) {
            builder.start(() -> {
                Task.findFibonacci(45);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
