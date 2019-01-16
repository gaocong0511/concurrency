package com.monkgow.concurrency.example.singleton;

import com.monkgow.concurrency.annotations.ThreadNotSafe;
import com.monkgow.concurrency.annotations.ThreadSafe;

/**
 * @Author: gaocong
 * @Date: 2019/01/15
 * @Description: 单例-->饿汉模式 单例实例在类装载的时候进行创建
 */
@ThreadSafe
public class SingletonExample2 {

    /**
     * 构造函数  私有
     */
    private SingletonExample2(){

    }

    //单例对象 如果类的构造函数之中有非常多的处理  那么在加载的时候就会非常的慢
    // 如果只进行了加载但是并没有进行调用的话 那么就会造成资源的浪费
    private static SingletonExample2 instance=new SingletonExample2();

    /**
     * 静态的工厂方法获取一个实例对象
     * @return
     */
    public synchronized static SingletonExample2 getInstance(){
        return instance;
    }
}
