package com.pfl.common.utils;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.pfl.common.entity.base.HttpResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by rocky on 2018/1/2.
 */

public class BaseObserver<T extends HttpResponse> implements Observer<T> {

    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        Utils.init(App.getInstance());
        this.mDisposable = d;
    }

    @Override
    public void onNext(T httpResponse) {
        if (httpResponse.isOk()) {
            onSuccess(httpResponse);
        } else {
            onFail(httpResponse);
        }
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showShort(e.getMessage());
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    public void onSuccess(T response) {

    }

    /**
     * 服务器返回数据，但响应码不为200
     *
     * @param response 服务器返回的数据
     */
    public void onFail(T response) {
        String message = response.getMsg();
        ToastUtils.showShort(message);
    }
}
