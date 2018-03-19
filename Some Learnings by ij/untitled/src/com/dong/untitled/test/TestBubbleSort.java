package com.dong.untitled.test;

import com.dong.untitled.singlecase.Singleton;

import java.util.Arrays;

public class TestBubbleSort {
    private static final int TEST_ARR[] = new int[]{1, 6, 3, 2, 20, 5};

    public static void main(String[] args) {
        testBubbleSort(TEST_ARR);
    }

    private static void testBubbleSort(int[] arr) {
        System.out.println("Before =" + Arrays.toString(TEST_ARR));
//        BubbleSort.bubbleSort(TEST_ARR);
//        doBubbleSort(TEST_ARR);
        Arrays.sort(TEST_ARR);
        System.out.println("Use Arrays.sort =" + Arrays.toString(TEST_ARR));
//        System.out.println("After Bubble Sort =" + Arrays.toString(TEST_ARR));
    }

    private static void testSingleton() {
        // 饿汉式单例模式
        Singleton singleton = Singleton.getInstance();
    }

    private static void doBubbleSort(int[] args) {
        int temp;
        for (int i = 0; i < args.length - 1; i++) {
            for (int j = 0; j < args.length - i - 1; j++) {
                // 从小到大升序
                if (args[j] > args[j+1]) {
                    temp = args[j];
                    args[j] = args[j+1];
                    args[j+1] = temp;
                }
            }
        }
    }

}
