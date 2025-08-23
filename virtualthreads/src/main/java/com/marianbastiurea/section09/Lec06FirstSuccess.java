package com.marianbastiurea.section09;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadLocalRandom;

public class Lec06FirstSuccess {
    private static final Logger logger = LoggerFactory.getLogger(Lec06FirstSuccess.class);

    public static void main(String[] args) {

        try(var taskScope = new StructuredTaskScope.ShutdownOnSuccess<>()){
            var subtask1 = taskScope.fork(Lec06FirstSuccess::failingTask);
            var subtask2 = taskScope.fork(Lec06FirstSuccess::getFrontierAirfare);

            taskScope.join();

            logger.info("subtask1 state: {}", subtask1.state());
            logger.info("subtask2 state: {}", subtask2.state());

            logger.info("subtask result: {}", taskScope.result(ex -> new RuntimeException("all failed")));


        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private static String getDeltaAirfare(){
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        logger.info("delta: {}", random);
        CommonUtil.sleep("delta", Duration.ofSeconds(3));
        return "Delta-$" + random;
    }

    private static String getFrontierAirfare(){
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        logger.info("frontier: {}", random);
        CommonUtil.sleep("frontier", Duration.ofSeconds(2));
        return "Frontier-$" + random;
    }

    private static String failingTask(){
        throw new RuntimeException("oops");
    }
}
