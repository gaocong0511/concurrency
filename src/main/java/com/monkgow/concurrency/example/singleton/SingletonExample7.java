package com.monkgow.concurrency.example.singleton;

import com.monkgow.concurrency.annotations.Recommend;
import com.monkgow.concurrency.annotations.ThreadSafe;

/**
 * @Author: gaocong
 * @Date: 2019/01/15
 * @Description:
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {
    //私有构造函数
    private SingletonExample7() {

    }

    private static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;

        //JVM保证其只被调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getSingleton() {
            return singleton;
        }
    }
}
