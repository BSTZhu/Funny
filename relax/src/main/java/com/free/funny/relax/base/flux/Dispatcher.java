package com.free.funny.relax.base.flux;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zhujunlin on 2017/5/26.
 */

public class Dispatcher {

    private final EventBus mBus;
    private static Dispatcher sInstance;

    private Dispatcher() {
        mBus = new EventBus();
    }

    public static Dispatcher getInstance() {
        if (sInstance == null) {
            sInstance = new Dispatcher();
        }
        return sInstance;
    }

    public void register(final Object o) {
        try {
            mBus.register(o);
        } catch (Exception e) {
        }
    }

    public void unregister(final Object o) {
        try {
            mBus.unregister(o);
        } catch (Exception e) {
        }
    }

    public void dispatch(BaseAction action) {
        mBus.post(action);
    }

    public void post(BaseEvent event) {
        mBus.post(event);
    }

    public void dispatchSticky(BaseAction action) {
        mBus.postSticky(action);
    }

    public void postSticky(BaseEvent event) {
        mBus.postSticky(event);
    }

    public <T> T getStickyEvent(Class<T> classType) {
        return mBus.getStickyEvent(classType);
    }

    public void removeStickyEvent(Object event) {
        mBus.removeStickyEvent(event);
    }
}
