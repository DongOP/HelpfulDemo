package com.dong.decorator.decorator;

/**
 * Created by Dong on 2017/10/24 0024.
 */

public class PureCoffee extends Coffee {

    // 单价10元一杯
    private int mPrice = 10;
    private String mName = getClass().getName();

    @Override
    public int getPrice() {
        return mPrice;
    }

    @Override
    public String getName() {
        return mName;
    }
}
