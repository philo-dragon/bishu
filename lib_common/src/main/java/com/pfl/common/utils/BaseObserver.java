package com.pfl.common.utils;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by rocky on 2018/1/2.
 */

public class BaseObserver<T> implements Observer<T> {

    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public void onNext(T httpResponse) {
        Log.e("BaseObserver", "onError");
    }

    @Override
    public void onError(Throwable e) {
        Utils.init(App.getInstance());
        ToastUtils.showShort(e.getMessage());
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        Log.e("BaseObserver", "onError");
    }

    @Override
    public void onComplete() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        Log.e("BaseObserver", "onComplete");
    }
}
