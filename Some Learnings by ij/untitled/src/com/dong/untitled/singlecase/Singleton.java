package com.dong.untitled.singlecase;

/**
 * 饿汉式单例模式
 * <p>
 * Created by Dong on 2018/1/10 0010.
 */
public class Singleton {

    private static final Singleton mSingleton = new Singleton();

    public static Singleton getInstance() {
        return mSingleton;
    }

}

    // 懒汉式单例 推荐写法
//public class Singleton {
//    private static class LazyHolder {
//        private static final Singleton INSTANCE = new Singleton();
//    }
//
//    public static final Singleton getInstance() {
//        return LazyHolder.INSTANCE;
//    }
//}
