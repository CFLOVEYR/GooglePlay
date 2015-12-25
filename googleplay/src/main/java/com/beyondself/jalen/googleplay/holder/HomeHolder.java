package com.beyondself.jalen.googleplay.holder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beyondself.jalen.googleplay.R;
import com.beyondself.jalen.googleplay.domain.AppInfo;
import com.beyondself.jalen.googleplay.utils.StringUtils;
import com.beyondself.jalen.googleplay.utils.UIUtils;

/**
 * =========================================================
 *
 *  版权: 个人开发Mr.Jalen  版权所有(c) ${YEAR}

 *  作者:${USER}

 *  版本: 1.0
 *
 *
 *  创建日期 : ${DATE}  ${HOUR}:${MINUTE}
 *
 *
 *  邮箱：Studylifetime@sina.com
 *
 *  描述:
 *
 *
 *  修订历史:
 *
 * =========================================================
 */
public class HomeHolder extends BaseHolder<AppInfo> {
    private RelativeLayout mItemTop;
    private ImageView mItemIcon;
    private RelativeLayout mItemAction;
    private FrameLayout mActionProgress;
    private TextView mActionTxt;
    private RelativeLayout mItemContent;
    private TextView mItemTitle;
    private RatingBar mItemRating;
    private TextView mItemSize;
    private View mItemDivider;
    private TextView mItemBottom;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item);
        mItemIcon = (ImageView) view.findViewById(R.id.item_icon);
        mItemTitle = (TextView) view.findViewById(R.id.item_title);
        mItemRating = (RatingBar) view.findViewById(R.id.item_rating);
        mItemSize = (TextView) view.findViewById(R.id.item_size);
        mItemBottom = (TextView) view.findViewById(R.id.item_bottom);
        return view;
    }

    @Override
    protected void refreshView() {
        AppInfo info = getmData();
        mItemTitle.setText(info.getName());
        mItemRating.setRating(info.getStars());
        mItemSize.setText(StringUtils.formatFileSize(info.getSize()));
        mItemBottom.setText(info.getDes());
    }
}
