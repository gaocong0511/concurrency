package com.monkgow.concurrency.example.syncContainer;

import com.monkgow.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

@Slf4j
@ThreadSafe
public class VectorExample2 {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10000; i++) {
                vector.add(i);
            }

            //开启一个线程往里面放入数据
            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });


            //开启一个线程 从vector之中取出数据
            Thread thread2 = new Thread(() -> {
                //这里如果取出的位置已经被删除掉了 那么就不能进行调用了
                for (int i = 0; i < vector.size(); i++) {
                    //log.info("" + vector.get(i));
                    vector.get(i);
                }
            });

            thread1.start();
            thread2.start();
        }
    }
}
