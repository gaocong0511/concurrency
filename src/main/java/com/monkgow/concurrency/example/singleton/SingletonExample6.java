package com.monkgow.concurrency.example.singleton;

import com.monkgow.concurrency.annotations.ThreadSafe;

/**
 * @Author: gaocong
 * @Date: 2019/01/15
 * @Description: 单例-->饿汉模式 单例实例在类装载的时候进行创建
 */
@ThreadSafe
public class SingletonExample6 {

    /**
     * 构造函数  私有
     */
    private SingletonExample6(){

    }
    //静态域和静态代码块的顺序一定要一样 如果不一样的话  那么就会出现没有成功的实例化的问题
    private static SingletonExample6 instance=null;

    static {
        instance=new SingletonExample6();
    }



    /**
     * 静态的工厂方法获取一个实例对象
     * @return
     */
    public synchronized static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());

    }
}
