package com.dong.decorator.decorator;

/**
 * Created by Dong on 2017/10/24 0024.
 */

public class MilkDecorator extends Decorator {

    /**
     * 通过组合的方式把Coffee对象传递进来
     *
     * @param coffee
     */
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getPrice() {
        // 添加牛奶需要多2块钱
        return mCoffee.getPrice() + 2;
    }

    @Override
    public String getName() {
        return "add Milk";
    }
}
