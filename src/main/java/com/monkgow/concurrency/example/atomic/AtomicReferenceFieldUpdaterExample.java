package com.monkgow.concurrency.example.atomic;

import com.monkgow.concurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Author: gaocong
 * @Date: 2018/12/20
 * @Description:
 */
@Slf4j
@ThreadSafe
public class AtomicReferenceFieldUpdaterExample {
    private static AtomicIntegerFieldUpdater<AtomicReferenceFieldUpdaterExample> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterExample.class,"count");

    private static AtomicReferenceFieldUpdaterExample example=new AtomicReferenceFieldUpdaterExample();

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        if(updater.compareAndSet(example,100,120)){
            log.info("update succeed:{}",example.getCount());
        }else {
            log.info("update faild");
        }
    }
}
