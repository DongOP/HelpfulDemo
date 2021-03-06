package com.dong.untitled.algorithm;

/**
 * 冒泡排序
 * <p>
 * Created by Dong on 2018/1/9 0009.
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) { // 趟数
            for (int j = 0; j < arr.length - i - 1; j++) { // j表示：第i趟，元素可进行的交换次数
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
