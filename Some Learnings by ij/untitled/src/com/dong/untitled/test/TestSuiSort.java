package com.dong.untitled.test;

import com.dong.untitled.algorithm.SuiShouJiSort;

/**
 * Created by Dong on 2018/1/17 0017.
 */
public class TestSuiSort {

    // 验证方法并打印结果
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 6, 6, 1, 1, 9, 2, 3, 3, 1};
        System.out.println("正整数数组的出现次数，按从大到小排序展示=" + SuiShouJiSort.suiSort(arr));
    }
}
