package com.monkgow.concurrency.example.singleton;

import com.monkgow.concurrency.annotations.NotRecommend;
import com.monkgow.concurrency.annotations.ThreadNotSafe;
import com.monkgow.concurrency.annotations.ThreadSafe;

/**
 * @Author: gaocong
 * @Date: 2019/01/15
 * @Description: 双重同步锁单例模式 限制不进行指令重排序
 */
@ThreadSafe
public class SingletonExample5 {

    /**
     * 构造函数  私有
     */
    private SingletonExample5() {

    }

    //单例对象
    private static volatile SingletonExample5 instance = null;

    /**
     * 静态的工厂方法获取一个实例对象 会造成更多的性能的开销
     *
     * @return
     */
    public static SingletonExample5 getInstance() {
        if (instance == null) {
            //单独锁这个类可以减少性能的开销 双重检测机制 加锁
            synchronized (SingletonExample5.class) {
                //再判断如果还是空的话 再进行实例化
                if (instance == null) {
                    instance = new SingletonExample5();
                    //1.memory=allocate()分配内存空间
                    //2.ctorInstance()初始化对象
                    //3.instance=memory 设置instance指向刚刚分配的内存
                    //如果在多线程的情况下进行了指令的重排序 JVM和CPU指令优化
                    //可能变成1 3 2这种顺序 那么判端控制的时候就会有问题
                    //有可能还没有完成初始化就被别的线程进行调用了
                    //概率不大 但是还是可能有问题的
                    //这里加上volatile之后避免指令重排序
                }
            }
        }
        return instance;
    }
}
