package com.marianbastiurea.section09;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.UUID;

public class Lec02ThreadLocal {
    private static final Logger logger = LoggerFactory.getLogger(Lec02ThreadLocal.class);
    private static final ThreadLocal<String> SESSION_TOKEN = new InheritableThreadLocal<>();

    public static void main(String[] args) {

        Thread.ofVirtual().name("virtual-1").start( () -> processIncomingRequest());
        Thread.ofVirtual().name("virtual-2").start( () -> processIncomingRequest());

        CommonUtil.sleep(Duration.ofSeconds(1));

    }


    private static void processIncomingRequest(){
        authenticate();
        controller();
    }

    private static void authenticate(){
        var token = UUID.randomUUID().toString();
        logger.info("token={}", token);
        SESSION_TOKEN.set(token);
    }

    private static void controller(){
        logger.info("controller: {}", SESSION_TOKEN.get());
        service();
    }

    private static void service(){
        logger.info("service: {}", SESSION_TOKEN.get());
        var threadName = "child-of-" + Thread.currentThread().getName();
        Thread.ofVirtual().name(threadName).start(Lec02ThreadLocal::callExternalService);
    }

    private static void callExternalService(){
        logger.info("preparing HTTP request with token: {}", SESSION_TOKEN.get());
    }

}
