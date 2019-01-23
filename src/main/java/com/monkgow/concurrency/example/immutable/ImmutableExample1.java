package com.monkgow.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.monkgow.concurrency.annotations.ThreadNotSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@ThreadNotSafe
public class ImmutableExample1 {
    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(2, 3);
    }

    public static void main(String[] args) {

        //a =2;
        //b="2"
        map.put(1, 3);
        //final在修饰实际的引用类型的时候  只是不允许指向另外的对象 但是对于这个值来说  可以进行改变的
        log.info("value  {}", map.get(1));
    }
}
