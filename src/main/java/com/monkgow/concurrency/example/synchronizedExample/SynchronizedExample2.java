package com.monkgow.concurrency.example.synchronizedExample;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: gaocong
 * @Date: 2019/01/03
 * @Description:
 */
@Slf4j
public class SynchronizedExample2 {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(()->{
            modifyStaticMethod();
        });
        executorService.execute(()->{
            modifyStaticMethod();
        });
    }

    /**
     * synchronized修饰代码块
     */
    public static void modifyClass(){
        synchronized (SynchronizedExample2.class){
            for (int i =0;i< 10;i++) {
                log.info("modifyClass - {}", i);
            }
        }
    }

    /**
     * synchronized修饰方法
     */
    public synchronized static void modifyStaticMethod(){
        for (int i =0;i< 10;i++) {
            log.info("modifyStaticMethod - {}", i);
        }
    }
}
