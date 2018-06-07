package com.pfl.common.weidget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pfl.common.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by rocky on 2018/6/7.
 */

public class CommonHeader extends FrameLayout implements RefreshHeader {

    private ImageView pre;
    private LottieAnimationView loading;
    private TextView tvRefreshInfo;
    private boolean isShowRefreshComplete;

    public CommonHeader(@NonNull Context context) {
        this(context, null);
    }

    public CommonHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.common_header, this, true);

        pre = findViewById(R.id.pre);
        loading = findViewById(R.id.loading);
        tvRefreshInfo = findViewById(R.id.tv_refresh_info);
    }

    private void init() {
        this.pre.setVisibility(View.GONE);
        this.loading.setVisibility(View.VISIBLE);
        this.tvRefreshInfo.setVisibility(View.GONE);
        this.loading.playAnimation();
    }

    private void onUIRefreshPrepare() {
        this.pre.setVisibility(View.VISIBLE);
        this.loading.setVisibility(View.GONE);
        this.tvRefreshInfo.setVisibility(View.GONE);
        this.loading.setProgress(0.0F);
        this.loading.cancelAnimation();
    }

    private void onUIRefreshComplete() {
        if (isShowRefreshComplete) {
            this.pre.setVisibility(View.GONE);
            this.loading.setVisibility(View.GONE);
            this.tvRefreshInfo.setVisibility(View.VISIBLE);
        }
    }

    public TextView getCompleteView() {
        return tvRefreshInfo;
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {

        Log.i("CommonHeader", "onPullingDown");

        if (percent > 1.0) {
            percent = 1;
        }

        pre.setScaleX(percent);
        pre.setScaleY(percent);
    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {
        Log.i("CommonHeader", "onReleasing");
    }

    @Override
    public void onRefreshReleased(RefreshLayout layout, int headerHeight, int extendHeight) {
        Log.i("CommonHeader", "onRefreshReleased");
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
        Log.i("CommonHeader", "onInitialized");
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
        Log.i("CommonHeader", "onHorizontalDrag");
    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
        Log.i("CommonHeader", "onStartAnimator");
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        Log.i("CommonHeader", "onFinish");
        return 200;//延迟100毫秒之后再弹回
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        Log.i("CommonHeader", "onStateChanged " + newState.toString());

        switch (newState) {
            case PullDownToRefresh://准备刷新
                onUIRefreshPrepare();
                break;
            case RefreshReleased://松开并刷新
                init();
                break;
            case RefreshFinish://刷新完成
                onUIRefreshComplete();
                break;
        }
    }
}
