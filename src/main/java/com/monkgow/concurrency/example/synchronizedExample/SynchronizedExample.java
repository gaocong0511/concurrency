package com.monkgow.concurrency.example.synchronizedExample;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: gaocong
 * @Date: 2019/01/03
 * @Description: synchronized 关键字实例
 */
@Slf4j
public class SynchronizedExample {
    public static void main(String[] args) {
        SynchronizedExample example =new SynchronizedExample();
        SynchronizedExample example2 =new SynchronizedExample();
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(()->{
            example.modifyCodeBlock();
        });

        executorService.execute(()->{
            example2.modifyCodeBlock();
        });
    }


    /**
     * synchronized修饰代码块
     */
    public void modifyCodeBlock(){
        synchronized (this){
            for (int i =0;i< 10;i++) {
                log.info("modifyCodeBlock - {}", i);
            }
        }
    }

    /**
     * synchronized修饰方法
     */
    public synchronized void modifyMethod(){
        for (int i =0;i< 10;i++) {
            log.info("modifyMethod - {}", i);
        }
    }
}
