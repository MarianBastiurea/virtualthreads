package com.marianbastiurea.section03;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CPUTaskDemo {
    public static final int TASK_COUNT =3* Runtime.getRuntime().availableProcessors();
    private static final Logger logger = LoggerFactory.getLogger(CPUTaskDemo.class);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            var totalTimeTaken= CommonUtil.timer(()->demo(Thread.ofVirtual()));
            logger.info("total time taken with virtual {}",totalTimeTaken);
            totalTimeTaken=CommonUtil.timer(()->demo(Thread.ofPlatform()));
            logger.info("total time taken with platform {}", totalTimeTaken);
        }


    }

    private static void demo(Thread.Builder builder) {
        var latch = new CountDownLatch(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++) {
            builder.start(() -> {
                Task.cpuIntensive(45);
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
