package com.marianbastiurea.section05;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Lec03SynchronizationWithIO {
    private static final Logger logger = LoggerFactory.getLogger(Lec03SynchronizationWithIO.class);


    static {
        System.setProperty("jdk.tracePinnedThreads", "full");
    }

    public static void main(String[] args) {
        Runnable runnable = () -> logger.info("Text message");
        demo(Thread.ofVirtual());
        Thread.ofVirtual().start(runnable);
        CommonUtil.sleep(Duration.ofSeconds(2));
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

    private static synchronized void ioTask() {

        CommonUtil.sleep(Duration.ofSeconds(10));
    }
}
