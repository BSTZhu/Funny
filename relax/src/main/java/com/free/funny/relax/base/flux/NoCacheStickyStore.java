package com.free.funny.relax.base.flux;

/**
 * Created by zhujunlin on 17/5/26.
 */

public abstract class NoCacheStickyStore extends NoCacheStore {

    protected abstract void onStickyAction(BaseAction action);

    protected abstract Class[] getStickyActions();

    @Override
    public void register() {
        super.register();
        Class<? extends BaseAction>[] cls = getStickyActions();
        if (cls == null || cls.length == 0) {
            return;
        }
        for (Class<? extends BaseAction> c : cls) {
            BaseAction action = mDispatcher.getStickyEvent(c);
            if (action != null) {
                onStickyAction(mDispatcher.getStickyEvent(c));
                mDispatcher.removeStickyEvent(action);
            }
        }
    }
}
