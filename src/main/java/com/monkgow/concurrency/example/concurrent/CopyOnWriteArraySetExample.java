package com.monkgow.concurrency.example.concurrent;

import com.monkgow.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author: gaocong
 * @Date: 2019/02/12
 * @Description:
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArraySetExample {
    private static Set<Integer> list=new CopyOnWriteArraySet<>();

    //总请求数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //计数
    public static StringBuffer stringBuilder = new StringBuffer();

    public static void main(String[] args) throws Exception {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count=i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("length:{}", list.size());
    }

    /**
     * 进行累加
     */
    private static void update(int i) {
        list.add(i);
    }
}
