package com.monkgow.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author: gaocong
 * @Date: 2019/02/18
 * @Description:
 */
@Slf4j
public class CyclicBarrierExample {
    private static CyclicBarrier barrier=new CyclicBarrier(5);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        CyclicBarrierExample cyclicBarrierExample=new CyclicBarrierExample();
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
        try{
        barrier.await(2000,TimeUnit.MILLISECONDS);}
        catch (BrokenBarrierException |TimeoutException e){
            log.info(e.getLocalizedMessage());
        }
        log.info("{} continue",threadNum);
    }
}
