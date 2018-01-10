package com.dong.untitled.simplefactorymode;

/**
 * 登录的管理类（简单工厂模式之 Factory 工厂角色）
 *
 * Created by Dong on 2018/1/10 0010.
 */
public class LoginFactory {
    public static final String PASSWORD_LOGIN_TYPE = "PASSWORD";
    public static final String QQ_LOGIN_TYPE = "WECHAT";

    public static Login factory(String loginType) {
        if (PASSWORD_LOGIN_TYPE.equals(loginType)) {
            return new PasswordLogin();
        } else if (QQ_LOGIN_TYPE.equals(loginType)) {
            return new WeChatLogin();
        } else {
            // 这里抛出一个自定义异常
            throw new RuntimeException("没有找到登录类型");
        }
    }
}
