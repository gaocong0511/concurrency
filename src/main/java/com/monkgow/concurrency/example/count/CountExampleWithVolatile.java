package com.monkgow.concurrency.example.count;

import com.monkgow.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: gaocong
 * @Date: 2019/01/03
 * @Description:
 */
@Slf4j
@ThreadSafe
public class CountExampleWithVolatile {
    //总请求数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //计数
    public static volatile int count = 0;

    public static void main(String[] args) throws Exception {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    /**
     * 进行累加
     */
    private static void add() {
        count++;
        //1.取count
        //2.+1
        //3.写回
    }
}
