package com.marianbastiurea.section09;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.UUID;

public class Lec03ScopedValues {
    private static final Logger logger = LoggerFactory.getLogger(Lec03ScopedValues.class);
    private static final ScopedValue<String> SESSION_TOKEN = ScopedValue.newInstance();

    public static void main(String[] args) {

        logger.info("isBound={}", SESSION_TOKEN.isBound());
        logger.info("value={}", SESSION_TOKEN.orElse("default value"));

        Thread.ofVirtual().name("1").start(Lec03ScopedValues::processIncomingRequest);
        Thread.ofVirtual().name("2").start(Lec03ScopedValues::processIncomingRequest);

        CommonUtil.sleep(Duration.ofSeconds(1));

    }

    private static void processIncomingRequest() {
        var token = authenticate();

        ScopedValue.runWhere(SESSION_TOKEN, token, () -> controller());
    }

    private static String authenticate() {
        var token = UUID.randomUUID().toString();
        logger.info("token={}", token);
        return token;
    }

    private static void controller() {
        logger.info("controller: {}", SESSION_TOKEN.get());
        service();
    }

    private static void service() {
        logger.info("service: {}", SESSION_TOKEN.get());
        ScopedValue.runWhere(SESSION_TOKEN, "new-token-" + Thread.currentThread().getName(), () -> callExternalService());
    }

    private static void callExternalService() {
        logger.info("preparing HTTP request with token: {}", SESSION_TOKEN.get());
    }

}
