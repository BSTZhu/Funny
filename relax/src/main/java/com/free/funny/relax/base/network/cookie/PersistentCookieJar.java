package com.free.funny.relax.base.network.cookie;


import com.free.funny.relax.logic.base.RLBaseApplication;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by xujian on 16/7/5.
 */
public class PersistentCookieJar implements CookieJar {

    private final PersistentCookieStore cookieStore = new PersistentCookieStore(RLBaseApplication.sAppContext);

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            for (Cookie item : cookies) {
                cookieStore.add(url, item);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }

    public PersistentCookieStore getCookieStore() {
        return cookieStore;
    }
}
