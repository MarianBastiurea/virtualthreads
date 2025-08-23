package com.marianbastiurea.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CommonUtil {


    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static void sleep(Duration duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static long timer(Runnable runnable){
        var start=System.currentTimeMillis();
        runnable.run();
        var end=System.currentTimeMillis();
        return end-start;
    }

    public static void sleep(String taskName, Duration duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            logger.info("{} is cancelled", taskName);
        }
    }
}
