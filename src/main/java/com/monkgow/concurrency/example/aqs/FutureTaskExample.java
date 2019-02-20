package com.monkgow.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask=new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something");
                Thread.sleep(5000);
                return "Done";
            }
        });

        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result :{}",result);


        //使用Runnable接口
    }
}
