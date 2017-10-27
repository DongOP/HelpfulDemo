package com.dong.decorator.decorator;

/**
 * Created by Dong on 2017/10/24 0024.
 */

public class SugarDecorator extends Decorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getPrice() {
        // 添加糖需要多1块钱
        return mCoffee.getPrice() + 1;
    }

    @Override
    public String getName() {
        return "add Sugar";
    }
}
