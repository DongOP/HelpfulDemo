package com.dong.observer.listener;

/**
 * 观察者接口
 *
 * Created by Dong on 2017/10/25 0025.
 */

public interface ObserverListener {

    /**
     * 刷新操作
     *
     * @param Content
     */
    void observerUpdate(String Content);
}
