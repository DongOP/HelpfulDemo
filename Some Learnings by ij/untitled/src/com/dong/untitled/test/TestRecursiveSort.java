package com.dong.untitled.test;

import com.dong.untitled.algorithm.RecursiveSort;

/**
 * Created by Dong on 2018/1/11 0011.
 */
public class TestRecursiveSort {

    public static void main(String[] args) {
//        testFibonacciSort();
//        testSum();
//        testFactorial();
        fSort(3);
    }

    private static void testFactorial() {
        System.out.println("5的阶乘=" + RecursiveSort.factorial(5));
    }

    private static void testSum() {
        System.out.println(RecursiveSort.increaseSum(1000));
    }

    private static void testFibonacciSort() {
        for (int i = 1; i < 10; i++) {
            System.out.println("斐波那契数列第" + i + "个=" + RecursiveSort.fibonacciSort(i));
        }
        System.out.println("斐波那契数列第30个=" + RecursiveSort.fibonacciSort(30));
    }

    private static int fSort(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fSort(n -1) + fSort(n - 2);
        }
    }
}
