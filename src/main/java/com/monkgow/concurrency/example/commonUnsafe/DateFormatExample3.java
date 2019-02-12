package com.monkgow.concurrency.example.commonUnsafe;

import com.monkgow.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: gaocong
 * @Date: 2019/02/12
 * @Description:
 */
@Slf4j
@ThreadSafe
public class DateFormatExample3 {

    //总请求数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws Exception {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    /**
     * 进行累加
     */
    private static void update() {
        try {
            //使用局部变量来实现局部封闭来避免多线程的时候会出现的问题
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20190212");
        } catch (ParseException e) {
            //e.printStackTrace();
            log.error("parse Exception",e);
        }
    }
}
