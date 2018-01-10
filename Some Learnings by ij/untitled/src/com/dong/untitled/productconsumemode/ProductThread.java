package com.dong.untitled.productconsumemode;

import java.util.List;

/**
 * Created by Dong on 2018/1/10 0010.
 */
public class ProductThread extends Thread {

    private List<ProductBean> mList;
    private int count = 0;

    public ProductThread(List<ProductBean> mList) {
        this.mList = mList;
    }

    @Override
    public void run() {
//        super.run();
        while (true) {
            // 休眠2s
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (mList) {
                while (mList.size() > 0) {
                    try {
                        mList.wait();
                        System.out.println("ProductThread-------库内有产品，生产者 wait()");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 没有产品，则开始生产，生产完后通知消费者线程
                while (mList.size() == 0) {
                    ProductBean product = new ProductBean();
                    count++;
                    product.setId(count);
                    product.setName("第" + count + "个产品");
                    mList.add(product);
                    // 通知消费者线程
                    mList.notify();
                    System.out.println("ProductThread-------生产了第" + count + "号产品  notify()");
                }
            }
        }
    }

}
