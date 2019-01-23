package com.monkgow.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.monkgow.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private  static Map<Integer,Integer> map= Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        map= Collections.unmodifiableMap(map);//其实是重新返回了一个UnmodifiableMap
    }

    public static void main(String[] args) {
        map.put(1,3);//此处会报错 因为我们已经将这个对象设置为了不可变对象 java.lang.UnsupportedOperationException
    }
}
