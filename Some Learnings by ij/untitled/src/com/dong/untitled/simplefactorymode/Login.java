package com.dong.untitled.simplefactorymode;

/**
 * 具体登录方式的共同父类(简单工厂模式之 Product 抽象产品角色)
 *
 * 简单工厂模式具体流程图 res--> mipmap --> simple_factory.png
 *
 * Created by Dong on 2018/1/10 0010.
 */
public interface Login {

    // 登录验证
    public boolean checkLogin (String username, String password);
}
