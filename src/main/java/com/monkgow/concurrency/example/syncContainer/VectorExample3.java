package com.monkgow.concurrency.example.syncContainer;

import com.monkgow.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

@Slf4j
@ThreadSafe
public class VectorExample3 {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        //同步容器
        //在对集合进行遍历的时候会有一个期望的值的大小  然后如果在变量的过程之中 集合的大小改变了的话 那么就会导致抛出异常
        //vector不可以的话 那么ArrayList是更加的不可以的
        //java.util.ConcurrentModificationException
        //test1(vector);

        //java.util.ConcurrentModificationException
        //test2(vector);

        //不会报错
        test3(vector);
    }

    //使用foreach循环
    private static void test1(Vector<Integer> vector){
        for (Integer i:vector) {
            if(i.equals(3)){
                vector.remove(i);
            }
        }
    }

    //使用迭代器 可以在遍历完成之后执行这一项操作
    private static void test2(Vector<Integer> vector){
        Iterator<Integer> iterator=vector.iterator();
        while(iterator.hasNext()){
            Integer i=iterator.next();
            if(i.equals(3)){
                vector.remove(i);
            }
        }
    }

    //使用普通的for循环 不报错
    private static void test3(Vector<Integer> vector){
        for(int i =0;i<vector.size();i++){
            if(vector.get(i).equals(3)){
                vector.remove(i);
            }
        }
    }
}
