package com.beyondself.jalen.googleplay.fragment;

import java.util.HashMap;

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
 * 创建日期 : 2015/12/23  20:33
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
public class FragmetFactory {
    private static HashMap<Integer, BaseFragment> mFragments = new HashMap<>();

    public static BaseFragment createFragment(int position) {
        //从集合中获得
        BaseFragment fragment = mFragments.get(position);
        //如果为空才开始从Fragment创建新的
        if (null == fragment) {

            switch (position) {
                case 0:
                    fragment = new HomeFragMent();
                    break;
                case 1:
                    fragment = new APPFragMent();
                    break;
                case 2:
                    fragment = new GameFragMent();
                    break;
                case 3:
                    fragment = new TopicFragMent();
                    break;
                case 4:
                    fragment = new RecommendFragMent();
                    break;
                case 5:
                    fragment = new SortFragMent();
                    break;
                case 6:
                    fragment = new RankFragMent();
                    break;
            }
            mFragments.put(position, fragment);
        }
        return fragment;
    }
}
