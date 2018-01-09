package com.dong.untitled.util;

/**
 * Created by Dong on 2018/1/9 0009.
 */
public class BubbleSort {

    // ## ![](/program_photos/bubble_parse.png)
    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) { // 趟数
            for (int j = 0; j < arr.length - i - 1; j++) { // j是在第i趟可交换的次数，j从0开始（0-->arr.length - i - 1)
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
