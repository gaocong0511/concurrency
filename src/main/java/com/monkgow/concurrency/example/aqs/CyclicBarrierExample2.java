package com.monkgow.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: gaocong
 * @Date: 2019/02/18
 * @Description:
 */
@Slf4j
public class CyclicBarrierExample2 {
    private static CyclicBarrier barrier=new CyclicBarrier(5);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        CyclicBarrierExample2 cyclicBarrierExample=new CyclicBarrierExample2();
        for(int i =0;i<10;i++){
            final int threadNum=i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                   race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws  Exception{
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        barrier.await();
        log.info("{} continue",threadNum);
    }
}
