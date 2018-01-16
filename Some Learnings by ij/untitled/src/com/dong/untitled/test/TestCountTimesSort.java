package com.dong.untitled.test;

import com.dong.untitled.algorithm.CountTimesSort;

/**
 * Created by Dong on 2018/1/11 0011.
 */
public class TestCountTimesSort {

    public static void main(String[] args) {
        Integer[] testArrays = {5, 10, 10, 5, 2, 5, 1};
        String[] testStrings = {"b", "d", "c", "a", "b", "b", "a"};
        CountTimesSort.countTimesSort(testStrings);
//        countArgs(testStrings);
    }

//    private static void countArgs(Object[] objs) {
//        if (objs.length == 0) {
//            return;
//        }
//
//        Map<Object, Integer> map = new HashMap<>();
//        for (Object arg : objs) {
//            Integer times = map.get(arg);
//            map.put(arg, times == null ? 1 : times + 1);
//        }
//        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
//            System.out.println("参数=" + entry.getKey() + ", 重复次数=" + entry.getValue());
//        }
//    }
}
