package com.beyondself.jalen.googleplay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.beyondself.jalen.googleplay.R;
import com.beyondself.jalen.googleplay.mannger.ThreadManager;
import com.beyondself.jalen.googleplay.utils.LogUtils;
import com.beyondself.jalen.googleplay.utils.UIUtils;

/**
 * =========================================================
 * <p/>
 * 版权: 个人开发Mr.Jalen  版权所有(c) 2015
 * <p/>
 * 作者: Jalen
 * <p/>
 * 版本: 1.0
 * <p/>
 * <p/>
 * 创建日期 : 2015/12/23  21:38
 * <p/>
 * <p/>
 * 邮箱：Studylifetime@sina.com
 * <p/>
 * 描述:
 * 用来加载页面的类
 * <p/>
 * 修订历史:
 * <p/>
 * =========================================================
 */
public abstract class LoadingFrameLayout extends FrameLayout {
    private static int UN_LOADING = 1;
    private static int LOADING = 2;
    private static int ERROR = 3;
    private static int EMPTY = 4;
    private static int SUCCESS = 5;
    private static int STATE;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;

    public LoadingFrameLayout(Context context) {
        super(context);
        init();
    }

    public LoadingFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //首先赋值
        STATE = UN_LOADING;
        //创建三个图像
        mLoadingView = createLoadingView();
        if (null != mLoadingView) {
            addView(mLoadingView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
        mErrorView = createErrorView();
        if (null != mErrorView) {
            addView(mErrorView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
        mEmptyView = createEmptyView();
        if (null != mEmptyView) {
            addView(mEmptyView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        }
        showSafePager();
    }

    private void showSafePager() {
        UIUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                showPager();
            }
        });
    }

    /**
     * 展示页面
     */
    private void showPager() {
        if (null != mLoadingView) {
            mLoadingView.setVisibility(STATE == UN_LOADING || STATE == LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (null != mErrorView) {
            mErrorView.setVisibility(STATE == ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (null != mEmptyView) {
            mEmptyView.setVisibility(STATE == EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if (null == mSuccessView && STATE == SUCCESS) {
           LogUtils.e("--------------------STATE == SUCCESS");

        }
        if (null != mSuccessView) {
            mSuccessView.setVisibility(STATE == SUCCESS ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /**
     * 让子类自己实现自己的布局,因为各有不同的布局
     */
    public abstract View createSuccessView();

    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    private View createErrorView() {
        return UIUtils.inflate(R.layout.loading_page_error);
    }

    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page_loading);
    }


    public void show() {
        if (STATE == ERROR || STATE == EMPTY) {
            STATE = UN_LOADING;
        }
        if (STATE == UN_LOADING) {
            STATE = LOADING;
            //            第一种方法-----开启单线程
            //            new Thread(){
            //                @Override
            //                public void run() {
            //                    super.run();
            //                }
            //            }.start();
            //            第二种方法---线程池
            //            ExecutorService service = Executors.newFixedThreadPool(SystemUtils.getCpuCount() * -1);
            //            MyTask task=new MyTask();
            //            service.execute(task);
            //            第三种种方法---自己封装好的线程池
            MyTask task = new MyTask();
            ThreadManager.getLongPool().execute(task);
        }
        //保证展现在主线程
        showSafePager();
    }

    /**
     * 下载数据
     */
    class MyTask implements Runnable {

        @Override
        public void run() {
            final LoadResult result = load();
            UIUtils.runInMainThread(new Runnable() {
                @Override
                public void run() {
                    STATE = result.getValue();
//                    LogUtils.e("---------"+STATE);
                    showPager();
                }
            });
        }
    }

    /**
     * 返回的结果
     */
    public enum LoadResult {
        ERROR(3), EMPTY(4), SUCCESS(5);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 子类自己实现,因为下载的数据不同
     */
    protected abstract LoadResult load();
}
