package com.pfl.common.utils;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * 防止Click事件重复点击
 * <p>
 * Created by rocky on 2018/4/16.
 */

public class RxClickUtil {

    /**
     * moren 500毫秒
     *
     * @param view
     * @param listener
     */
    public static void RxClick(final View view, final View.OnClickListener listener) {
        RxClick(500, view, listener);
    }

    /**
     * @param duration 多长时间内点击无效
     * @param view     要点击的view
     * @param listener OnClickListener
     */
    public static void RxClick(long duration, final View view, final View.OnClickListener listener) {

        RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                listener.onClick(view);
            }
        });
    }

}
