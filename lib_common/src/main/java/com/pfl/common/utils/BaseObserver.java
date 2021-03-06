package com.pfl.common.utils;

import android.net.ParseException;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.google.gson.JsonParseException;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.exception.NoNetworkException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

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

        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof NoNetworkException) {
            onException(ExceptionReason.NOT_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR);
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
        switch (response.getCode()) {
            case 401://表示未登录
                RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_LOGIN);
                break;
        }

    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 没有网络
         */
        NOT_NETWORK,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 空数据
         */
        EMPTY_DATA,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
