package com.marianbastiurea.section05;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Lec01RaceCondition {
    private static final Logger logger = LoggerFactory.getLogger(Lec01RaceCondition.class);
    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        demo(Thread.ofVirtual());
        CommonUtil.sleep(Duration.ofSeconds(2));
        logger.info("list size {}",  list.size());


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


    private static synchronized void inMemoryTask() {
        list.add(1);
    }
}
