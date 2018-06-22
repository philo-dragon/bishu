package com.pfl.common.weidget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.pfl.common.http.RxSchedulers;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * Created by rocky on 2018/6/21.
 */

public class RefreshLayout extends SmartRefreshLayout implements OnRefreshListener {

    private CommonHeader commonHeader;
    private OnRefreshListener mOnRefreshListener;

    public RefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        commonHeader = new CommonHeader(context);
        super.setOnRefreshListener(this);
        super.setRefreshHeader(commonHeader);

    }

    @Override
    public void onRefresh(final com.scwang.smartrefresh.layout.api.RefreshLayout refreshlayout) {

        Observable.just(1)
                .delay(500, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.<Integer>noCheckNetworkCompose())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        commonHeader.getCompleteView().setVisibility(View.VISIBLE);
                        refreshlayout.finishRefresh(200);
                        if (mOnRefreshListener != null) {
                            mOnRefreshListener.finishRefresh();
                        }
                    }
                });


    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.mOnRefreshListener = listener;
    }

    public interface OnRefreshListener {
        void finishRefresh();
    }
}
