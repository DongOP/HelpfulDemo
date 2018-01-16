package com.dong.untitled.productconsumemode;

import java.util.List;

/**
 * Created by Dong on 2018/1/10 0010.
 */
public class ConsumeThread extends Thread {
    private List<ProductBean> mList;

    public ConsumeThread(List<ProductBean> mList) {
        this.mList = mList;
    }

    @Override
    public void run() {
//        super.run();
        while (true) {
            synchronized (mList) {
                // 队列中有产品时，消费者就取出来
                while (mList.size() > 0) {
                    for (int i = 0; i < mList.size(); i++) {
                        mList.remove(i);
                        System.out.println("------ConsumeThread---------消费者取出了产品 notify()");
                        mList.notify();
                    }
                }
                while (mList.size() == 0) {
                    try {
                        System.out.println("------ConsumeThread---------队列中已没有了产品，请继续生产！进入wait()");
                        mList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
