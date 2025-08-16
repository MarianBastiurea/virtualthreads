package com.marianbastiurea.section05;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lec05ReentrantLockWithIO {
    private static final Logger logger = LoggerFactory.getLogger(Lec05ReentrantLockWithIO.class);
    private static final Lock lock = new ReentrantLock(true);

    static {
        System.setProperty("jdk.tracePinnedThreads", "full");
    }

    public static void main(String[] args) {
        Runnable runnable = () -> logger.info("Text message");

        demo(Thread.ofVirtual());
        Thread.ofVirtual().start(runnable);
        CommonUtil.sleep(Duration.ofSeconds(15));
    }

    private static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                logger.info("task started {}", Thread.currentThread());
                ioTask();
                logger.info("task ended {}", Thread.currentThread());

            });
        }

    }

    private static void ioTask() {
        try {
            lock.lock();
            CommonUtil.sleep(Duration.ofSeconds(10));
        } catch (Exception e) {
            logger.info("error", e);
        } finally {
            lock.unlock();
        }

    }
}
