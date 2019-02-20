package com.monkgow.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService= Executors.newScheduledThreadPool(5);

       executorService.schedule(() -> log.info("schedule run after 3 seconds"),3, TimeUnit.SECONDS);

       //延迟一秒开始启动  然后之后就每隔3s执行一次
       executorService.scheduleAtFixedRate(()->log.info("fixedRateRun"),1,3,TimeUnit.SECONDS);

        //executorService.shutdown();

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        },new Date(),5*1000);
    }
}
