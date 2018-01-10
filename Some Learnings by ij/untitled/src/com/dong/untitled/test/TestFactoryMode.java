package com.dong.untitled.test;

import com.dong.untitled.simplefactorymode.Login;
import com.dong.untitled.simplefactorymode.LoginFactory;


/**
 * Created by Dong on 2018/1/10 0010.
 */
public class TestFactoryMode {

    public static void main(String[] args){
        testFactory();
    }

    private static void testFactory() {
        String name = "name";
        String password = "password";
        // 选择登录模式
        Login login = LoginFactory.factory(LoginFactory.PASSWORD_LOGIN_TYPE);
        // 验证登录信息
        boolean loginFlag = login.checkLogin(name, password);
        if (loginFlag) {
            System.out.println("进入登录通过的界面.");
        } else {
            System.out.println("请检查账号密码重新登录!");
        }
    }
}
