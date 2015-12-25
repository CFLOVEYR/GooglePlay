package com.beyondself.jalen.googleplay.holder;

import android.view.View;

/**
 * =========================================================
 * <p/>
 * 版权: 个人开发Mr.Jalen  版权所有(c) ${YEAR}
 * <p/>
 * 作者:${USER}
 * <p/>
 * 版本: 1.0
 * <p/>
 * <p/>
 * 创建日期 : ${DATE}  ${HOUR}:${MINUTE}
 * <p/>
 * <p/>
 * 邮箱：Studylifetime@sina.com
 * <p/>
 * 描述:
 * <p/>
 * <p/>
 * 修订历史:
 * <p/>
 * =========================================================
 */
public abstract class BaseHolder<T> {
    private T mData;

    private final View view;

    public BaseHolder() {
        view = initView();
        view.setTag(this);
    }

    public View getRootView() {
        return view;
    }

    public abstract View initView();

    public void setData(T mData) {
        this.mData = mData;
        refreshView();
    }

    protected abstract void refreshView();

    public T getmData() {
        return mData;
    }
}
