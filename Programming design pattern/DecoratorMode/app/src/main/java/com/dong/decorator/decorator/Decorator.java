package com.dong.decorator.decorator;

/**
 * Created by Dong on 2017/10/24 0024.
 */

public abstract class Decorator extends Coffee{

    protected Coffee mCoffee;

    /**
     * 通过组合的方式把Coffee对象传递进来
     *
     * @param coffee
     */
    public Decorator(Coffee coffee) {
        mCoffee = coffee;
    }
}
