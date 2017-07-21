package com.free.funny.base.common;

/**
 * Created by zhujunlin on 2017/7/20.
 * 应用环境类
 */

public class Constants {

    public static final boolean DEBUG = true;

    public static MODEL ENV = MODEL.TEST;

    private static final String DEV_TEST = "https://test.bldz.com/";
    private static final String DEV_DEV = "http://dev.bldz.com/";
    private static final String DEV_RELEASE = "https://pro.bldz.com/";
    private static final String DEV_PRD = "https://www.bldz.com/";

    //不同模块会有不同的端口
    public static final String BASIC = "basic";
    public static final String PAY = "pay";
    public static final String BIDDING = "0";
    public static final String WORKBENCH = "workbench";
    public static final String INFORMATION = "information";
    public static final String INTENTION = "intention"; //意向
    public static final String TRANSACTION = "transaction"; //交易
    public static final String ORDER = "order"; //订单
    public static final String SHOP = "shop";
    public static final String PLATFORM = "platform";
    public static final String WEBPAGEPREFIX = "app/web/"; //资讯、商品详情地址前缀
    public static final String IMAGEHEAD = "imageHead"; //头像地址
    public static final String OPEN = "open"; //绑定第三方账号校验
    public static final String SHARE = "share"; //分享
    public static final String SOCIALITY = "sociality";
    public static final String MAINURL = "mainurl";
    public static final String PRODUCT = "product"; //绑定第三方账号校验
    public static final String TURN = "turn"; //快转

    public static String getStartPoint() {
        switch (ENV) {
            case PRD:
                return DEV_PRD;
            case TEST:
                return DEV_TEST;
            case DEV:
                return DEV_DEV;
            case RELEASE:
                return DEV_RELEASE;
        }
        return DEV_TEST;
    }

    public enum MODEL {
        PRD,
        TEST,
        DEV,
        RELEASE
    }
}
