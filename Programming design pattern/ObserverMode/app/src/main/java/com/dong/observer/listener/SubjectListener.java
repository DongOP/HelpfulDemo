package com.dong.observer.listener;

/**
 * Created by Dong on 2017/10/25 0025.
 *
 * 被观察者接口
 */

public interface SubjectListener {

    // add 观察者
    void add(ObserverListener observerListener);
    // 更新数据
    void notifyObserver(String content);
    // 移除观察者
    void remove(ObserverListener observerListener);
}
