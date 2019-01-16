package com.monkgow.concurrency.example.singleton;

import com.monkgow.concurrency.annotations.ThreadNotSafe;

/**
 * @Author: gaocong
 * @Date: 2019/01/15
 * @Description: 单例-->懒汉模式
 */
@ThreadNotSafe
public class SingletonExample1 {

    /**
     * 构造函数  私有
     */
    private SingletonExample1(){

    }

    //单例对象
    private static SingletonExample1 instance=null;

    /**
     * 静态的工厂方法获取一个实例对象
     * @return
     */
    public static SingletonExample1 getInstance(){
        if(instance==null){
            instance=new SingletonExample1();
        }
        return instance;
    }
}
