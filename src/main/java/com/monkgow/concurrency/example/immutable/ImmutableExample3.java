package com.monkgow.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.monkgow.concurrency.annotations.ThreadNotSafe;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@ThreadNotSafe
public class ImmutableExample3 {
    //这个的长度是可以一直写下去的 这里也可以声明为普通的list对象 但是下面的操作也是不可以的 虽然在IDE之中不会提示  但是会报错
    private final static ImmutableList list = ImmutableList.of(1, 2, 3, 4, 5);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    //键值对的形式出现来进行添加
    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1, 2, 3, 4);

    //使用builder的方式来逐个进行添加
    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).build();

    public static void main(String[] args) {
        list.add(1);//在IDE之中就会被提示不推荐使用了
    }
}
