package com.marianbastiurea.section03;

import com.marianbastiurea.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
  private static final  Logger logger= LoggerFactory.getLogger(Task.class);

  public static long findFibonacci(long number){
      if(number<2){
          return number;
      } else{
          return findFibonacci(number-1)+findFibonacci(number-2);
      }
  }

  public static void cpuIntensive(int i){
     // logger.info("starting cpu task. Thread info:{} ", Thread.currentThread());
      var timeTaken= CommonUtil.timer(()->findFibonacci(i));
     // logger.info("ending cpu task.Thread info: {}", timeTaken);
  }

}
