package com.monkgow.concurrency.example.commonUnsafe;

import com.monkgow.concurrency.annotations.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
@ThreadNotSafe
public class DateFormatExample {
    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
    //总请求数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;

    private static DateTimeFormatter dateTimeFormatter= DateTimeFormat.forPattern("yyyyMMdd");

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
        //log.info(simpleDateFormat.format("20190212"));
        log.info(""+DateTime.parse("20190212",dateTimeFormatter));
    }
}
