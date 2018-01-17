package com.dong.untitled.algorithm;

import java.util.*;

/**
 * 求出正整数数组的出现次数，按从大到小排序展示
 * <p>
 * Created by Dong on 2018/1/17 0017.
 */
public class SuiShouJiSort {

    public static List<Integer> suiSort(int[] args) {
        // 将数组内的正整数、出现次数以键值对形式存放在Map中
        Map<Integer, Integer> mMap = new HashMap<>();
        for (int arg : args) {
            Integer times = mMap.get(arg);
            mMap.put(arg, times == null ? 1 : times + 1);
        }

        // 将整数出现的次数放置在List中
        List<Integer> mList = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : mMap.entrySet()) {
            mList.add(entry.getValue());
        }
        // 从大到小排序
        sortListDesc(mList);

        return mList;
    }

    // 对出现的次数进行排序(从大到小)
    private static List<Integer> sortListDesc(List<Integer> list) {
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 返回相反的compare
                return o2.compareTo(o1);
            }
        });
        return list;
    }

}
