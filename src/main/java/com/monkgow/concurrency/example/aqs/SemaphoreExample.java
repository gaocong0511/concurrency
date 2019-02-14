package com.monkgow.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author: gaocong
 * @Date: 2019/02/14
 * @Description:
 */
@Slf4j
public class SemaphoreExample {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore=new Semaphore(2);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();//获取许可
                    test(threadNum);
                    semaphore.release();//释放许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("end");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("currentThreadNum{}", threadNum);
        Thread.sleep(1000);

    }
}
