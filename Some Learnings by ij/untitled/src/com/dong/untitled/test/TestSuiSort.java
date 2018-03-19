package com.dong.untitled.test;

import java.util.*;

/**
 * Created by Dong on 2018/1/17 0017.
 */
public class TestSuiSort {

    // 验证方法并打印结果
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 6, 6, 1, 1, 9, 2, 3, 3, 1};
//        System.out.println("正整数数组的出现次数，按从大到小排序展示=" + SuiShouJiSort.suiSort(arr));
        System.out.println("正整数数组的出现次数，按从大到小排序展示=" + suiSort(arr));

    }

    public static List<Integer> suiSort(int[] args) {
        List<Integer> mList = new ArrayList<>();
        Map<Integer, Integer> mMap = new HashMap<>();
        // 数组参数及其出现的次数以键值对形式存储
        for (int arg : args) {
            Integer times = mMap.get(arg);
            mMap.put(arg, times == null ? 1 : times + 1);
        }
        // 参数出现的次数放置在List中
        for (Map.Entry<Integer, Integer> entry : mMap.entrySet()) {
            mList.add(entry.getValue());
        }

        // 从大到小排序List
        sortByDesc(mList);

        return mList;
    }

    private static void sortByDesc(List<Integer> list) {
        // java 8
        Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
    }
}
