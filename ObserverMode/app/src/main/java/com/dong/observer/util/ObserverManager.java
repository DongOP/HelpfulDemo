package com.dong.observer.util;

import com.dong.observer.listener.ObserverListener;
import com.dong.observer.listener.SubjectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者的管理类
 * <p>
 * Created by Dong on 2017/10/25 0025.
 */

public class ObserverManager implements SubjectListener {

    private static final ObserverManager mObserverManager = new ObserverManager();
    // 观察者接口集合
    private List<ObserverListener> mObserverList = new ArrayList<>();

    private ObserverManager() {}

    /**
     * 单例
     */
    public static ObserverManager getInstance() {
        return mObserverManager;
    }

    @Override
    public void add(ObserverListener observerListener) {
        mObserverList.add(observerListener);

    }

    @Override
    public void notifyObserver(String content) {
        for (ObserverListener observerListener : mObserverList) {
            observerListener.observerUpdate(content);
        }
    }

    @Override
    public void remove(ObserverListener observerListener) {
        if (mObserverList.contains(observerListener)) {
            mObserverList.remove(observerListener);
        }
    }
}
