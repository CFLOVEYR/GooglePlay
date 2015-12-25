package com.beyondself.jalen.googleplay.protocol;


import android.os.SystemClock;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by mwqi on 2014/6/7.
 */
public abstract class BaseProtocol<Data> {
    public static final String cachePath = "";

    /**
     * 加载协议
     */
    public Data load(int index) {
        SystemClock.sleep(1000);// 休息1秒，防止加载过快，看不到界面变化效果
        String json = null;
        // 2.如果缓存时间过期，从网络加载
        json = loadFromNet(index);
        return parseFromJson(json);
    }

    /**
     * 从网络加载协议
     */
    protected String loadFromNet(int index) {
        String url = "http://127.0.0.1:8090/home?index=" + index;
        final String[] result = new String[1];
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Request request, Exception e)
                    {

                    }

                    @Override
                    public void onResponse(String response)
                    {
                            result[0] =response;
                    }
                });
        return result[0];
    }


    /**
     * 需要增加的额外参数
     */
    protected String getParames() {
        return "";
    }

    /**
     * 该协议的访问地址
     */
    protected abstract String getKey();

    /**
     * 从json中解析
     */
    protected abstract Data parseFromJson(String json);
}
