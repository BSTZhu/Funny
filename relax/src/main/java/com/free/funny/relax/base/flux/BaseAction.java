package com.free.funny.relax.base.flux;

import java.io.Serializable;

/**
 * Created by zhujunlin on 2017/5/26.
 */

public abstract class BaseAction implements Serializable {

    public abstract <T> T getData();

}
