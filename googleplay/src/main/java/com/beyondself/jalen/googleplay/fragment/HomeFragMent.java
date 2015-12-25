package com.beyondself.jalen.googleplay.fragment;

import android.view.View;
import android.widget.ListView;

import com.beyondself.jalen.googleplay.adapter.MyBaseAdapter;
import com.beyondself.jalen.googleplay.domain.AppInfo;
import com.beyondself.jalen.googleplay.holder.BaseHolder;
import com.beyondself.jalen.googleplay.holder.HomeHolder;
import com.beyondself.jalen.googleplay.protocol.HomeProtocol;
import com.beyondself.jalen.googleplay.utils.UIUtils;
import com.beyondself.jalen.googleplay.widget.LoadingFrameLayout;

import java.util.List;

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
 * 创建日期 : 2015/12/23  20:27
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
public class HomeFragMent extends BaseFragment {

    private ListView mListView;
    private List<AppInfo> mData;

    @Override
    public View createSuccessView() {
        mListView = new ListView(UIUtils.getContext());
        HomeAdapter adapter = new HomeAdapter(mListView, mData);
        mListView.setAdapter(adapter);
        return mListView;
    }

    @Override
    protected LoadingFrameLayout.LoadResult loadData() {
        HomeProtocol protocol = new HomeProtocol();
        mData = protocol.load(0);
        return check(mData);
    }

    class HomeAdapter extends MyBaseAdapter {
        public HomeAdapter(ListView mListView, List<AppInfo> mData) {
            super(mListView, mData);
        }

        @Override
        protected BaseHolder getHolder() {
            return new HomeHolder();
        }
    }

}
