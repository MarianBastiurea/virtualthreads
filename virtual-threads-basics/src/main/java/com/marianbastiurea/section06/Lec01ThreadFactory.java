package com.marianbastiurea.section06;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

public class Lec01ThreadFactory {
    private static final Logger logger = LoggerFactory.getLogger(Lec01ThreadFactory.class);


    public static void main(String[] args) {
        demo(Thread.ofVirtual().name("aliBaba", 1).factory());
        CommonUtil.sleep(Duration.ofSeconds(2));
    }

    private static void demo(ThreadFactory factory) {
        for (int i = 0; i < 4; i++) {
            var t = factory.newThread(() -> {
                logger.info("start parent thread {}", Thread.currentThread());
                var ct = factory.newThread(() -> {
                    logger.info("start new child thread {}", Thread.currentThread());
                    CommonUtil.sleep(Duration.ofSeconds(2));
                    logger.info("end child thread {}", Thread.currentThread());
                });
                ct.start();
                logger.info("end parent thread {}", Thread.currentThread());
            });
            t.start();
        }
    }
}
