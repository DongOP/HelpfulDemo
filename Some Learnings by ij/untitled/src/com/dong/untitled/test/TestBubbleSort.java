package com.dong.untitled.test;

import com.dong.untitled.algorithm.BubbleSort;
import com.dong.untitled.singlecase.Singleton;

import java.util.Arrays;

public class TestBubbleSort {
    private static final int TEST_ARR[] = new int[]{1, 6, 3, 2, 20, 5};

    public static void main(String[] args) {
        testBubbleSort(TEST_ARR);
    }

    private static void testBubbleSort(int[] arr) {
        System.out.println("Before =" + Arrays.toString(TEST_ARR));
        BubbleSort.bubbleSort(TEST_ARR);
        System.out.println("After Bubble Sort =" + Arrays.toString(TEST_ARR));
    }

    private static void testSingleton() {
        // 饿汉式单例模式
        Singleton singleton = Singleton.getInstance();
    }

}
