package com.free.funny.base;

import java.io.Serializable;

/**
 * Created by zhujunlin on 17/7/3.
 */
public class BaseModel implements Serializable {
    public String returnFlag = "";
    public String message;
    public String code;
    public boolean success;
}
