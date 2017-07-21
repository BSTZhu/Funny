package com.free.funny.platform.model;

/**
 * Created by zhujunlin on 2017/7/4.
 */

public class LiveListBean {
    public int offset; //翻页偏移量  默认 0
    public int limit; //每次获取数量(limit<1 或 者 limit>100 时，limit = 30)
}
