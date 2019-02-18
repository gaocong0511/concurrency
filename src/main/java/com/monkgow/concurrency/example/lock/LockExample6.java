package com.monkgow.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExample6 {
    public static void main(String args[]){
        ReentrantLock reentrantLock=new ReentrantLock();
        Condition condition=reentrantLock.newCondition();

        new Thread(()->{
            try{
                reentrantLock.lock();//线程加入到aqs的等待队列之中去
                log.info("wait signal");
                condition.await(); //线程就从aqs队列之中移除了 并加入到condition的等待队列之中去
            }catch (InterruptedException e){
                log.info("error");
            }finally {

            }
            log.info("get signal");
            reentrantLock.unlock();
        }).start();

        new Thread(()->{
            reentrantLock.lock();
            log.info("get lock");
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                log.info("error");
            }
            condition.signalAll();
            log.info("send single~");
            reentrantLock.unlock();
        }).start();
    }
}
