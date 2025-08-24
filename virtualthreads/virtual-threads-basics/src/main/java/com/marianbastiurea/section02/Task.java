package com.marianbastiurea.section02;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    private static final Logger log = LoggerFactory.getLogger(com.marianbastiurea.section01.Task.class);

    public static void execute(int i) {
        log.info("starting execute task {}", i);
        try {
            method1(i);
        } catch (Exception e) {
            log.error("log error for {}", i, e);
        }
        log.info("ending execute task {}", i);

    }

    private static void method1(int i) {
        CommonUtil.sleep(Duration.ofMillis(300));
        try {
            method2(i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void method2(int i) {
        CommonUtil.sleep(Duration.ofMillis(100));
        method3(i);
    }

    private static void method3(int i) {
        CommonUtil.sleep(Duration.ofMillis(500));
        if (i == 4) {
            throw new IllegalArgumentException("I can not be 4");
        }
    }
}
