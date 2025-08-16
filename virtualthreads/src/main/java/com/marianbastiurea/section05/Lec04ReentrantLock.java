package com.marianbastiurea.section05;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lec04ReentrantLock {
    private static final Logger logger = LoggerFactory.getLogger(Lec04ReentrantLock.class);
    private static final List<Integer> list = new ArrayList<>();
    private static final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        demo(Thread.ofVirtual());
        CommonUtil.sleep(Duration.ofSeconds(2));
        logger.info("list size {}", list.size());


    }

    private static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                logger.info("task started {}", Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    inMemoryTask();
                }
                logger.info("task ended {}", Thread.currentThread());

            });
        }

    }

    private static void inMemoryTask() {
        try {
            lock.lock();
            list.add(1);
        } catch (Exception e) {
            logger.info("error", e);
        } finally {
            lock.unlock();
        }

    }
}
