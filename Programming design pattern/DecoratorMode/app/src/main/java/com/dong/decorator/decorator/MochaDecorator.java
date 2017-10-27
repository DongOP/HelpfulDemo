package com.dong.decorator.decorator;

/**
 * Created by Dong on 2017/10/24 0024.
 */

public class MochaDecorator extends Decorator {

    public MochaDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getPrice() {
        // 添加抹茶需要多4块钱
        return mCoffee.getPrice() + 4;
    }

    @Override
    public String getName() {
        return "add Mocha";
    }
}
