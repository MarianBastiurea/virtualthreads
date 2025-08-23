package com.marianbastiurea.section09;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadLocalRandom;

public class Lec04SuccessOrFailure {
    private static final Logger logger = LoggerFactory.getLogger(Lec04SuccessOrFailure.class);

    public static void main(String[] args) {

        try (var taskScope = new StructuredTaskScope<>()) {
            var subtask1 = taskScope.fork(Lec04SuccessOrFailure::getDeltaAirfare);
            var subtask2 = taskScope.fork(Lec04SuccessOrFailure::failingTask);

            taskScope.join();

            logger.info("subtask1 state: {}", subtask1.state());
            logger.info("subtask2 state: {}", subtask2.state());

            logger.info("subtask1 result: {}", subtask1.get());
            logger.info("subtask2 result: {}", subtask2.get());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static String getDeltaAirfare() {
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        logger.info("delta: {}", random);
        CommonUtil.sleep("delta", Duration.ofSeconds(1));
        return "Delta-$" + random;
    }

    private static String getFrontierAirfare() {
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        logger.info("frontier: {}", random);
        CommonUtil.sleep("frontier", Duration.ofSeconds(2));
        return "Frontier-$" + random;
    }

    private static String failingTask() {
        throw new RuntimeException("oops");
    }

}
