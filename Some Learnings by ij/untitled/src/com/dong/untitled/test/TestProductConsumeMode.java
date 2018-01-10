package com.dong.untitled.test;

import com.dong.untitled.productconsumemode.ConsumeThread;
import com.dong.untitled.productconsumemode.ProductBean;
import com.dong.untitled.productconsumemode.ProductThread;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dong on 2018/1/10 0010.
 */
public class TestProductConsumeMode {

    public static void main(String[] args) {
        // 创建集合
        List<ProductBean> productList = new LinkedList<ProductBean>();
        // 初始化并开启线程
        ProductThread productThread = new ProductThread(productList);
        ConsumeThread consumeThread = new ConsumeThread(productList);
        productThread.start();
        consumeThread.start();
    }
}
