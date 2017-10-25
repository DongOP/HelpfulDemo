package com.dong.decorator.decorator;

/**
 * Created by Dong on 2017/10/24 0024.
 */

public class MilkBubbleDecorator extends Decorator {

    public MilkBubbleDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getPrice() {
        // 添加牛奶泡需要多3块钱
        return mCoffee.getPrice() + 3;
    }

    @Override
    public String getName() {
        return "add Milk Bubble";
    }
}
