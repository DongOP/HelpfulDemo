package com.dong.untitled.util;

/**
 * Created by Dong on 2018/1/9 0009.
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) { // 趟数
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 大的数字往后移换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
