package com.monkgow.concurrency.example.singleton;

import com.monkgow.concurrency.annotations.NotRecommend;
import com.monkgow.concurrency.annotations.ThreadNotSafe;
import com.monkgow.concurrency.annotations.ThreadSafe;

/**
 * @Author: gaocong
 * @Date: 2019/01/15
 * @Description: 单例-->懒汉模式
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    /**
     * 构造函数  私有
     */
    private SingletonExample3(){

    }

    //单例对象
    private static SingletonExample3 instance=null;

    /**
     * 静态的工厂方法获取一个实例对象 会造成更多的性能的开销
     * @return
     */
    public static synchronized SingletonExample3 getInstance(){
        if(instance==null){
            instance=new SingletonExample3();
        }
        return instance;
    }
}
