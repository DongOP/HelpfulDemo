package com.dong.untitled.simplefactorymode;

/**
 * 微信登录方式（简单工厂模式之 ConcreteProduct 具体产品角色）
 *
 * Created by Dong on 2018/1/10 0010.
 */
public class WeChatLogin implements Login {

    @Override
    public boolean checkLogin(String username, String password) {
        System.out.println("选择微信登录方式...WeChatLogin");
        // 具体的业务逻辑代码
        if (!username.isEmpty() && !password.isEmpty()) {
            System.out.println("验证通过");
            return true;
        } else {
            System.out.println("验证失败");
            return false;
        }
    }
}
