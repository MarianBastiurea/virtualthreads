package com.marianbastiurea.section04;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CooperativeSchedulingDemo {
    private static final Logger logger = LoggerFactory.getLogger(CooperativeSchedulingDemo.class);

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
    }

    public static void main(String[] args) {
        var threadBuilder = Thread.ofVirtual();
        var t1 = threadBuilder.unstarted(() -> demo(1));
        var t2 = threadBuilder.unstarted(() -> demo(2));
        var t3 = threadBuilder.unstarted(() -> demo(3));
        t1.start();
        t2.start();
        t3.start();
        CommonUtil.sleep(Duration.ofSeconds(2));
    }


    private static void demo(int threadNumber) {
        logger.info("thread - {} started", threadNumber);
        for (int i = 0; i < 10; i++) {
            logger.info("Thread {} is printing {}. Thread number {}", threadNumber, i, Thread.currentThread());
            Thread.yield();
        }
        logger.info("Thread {} ended", threadNumber);

    }

}
