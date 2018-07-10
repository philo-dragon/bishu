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

import static com.pfl.common.utils.BaseObserver.ExceptionReason.CONNECT_ERROR;
import static com.pfl.common.utils.BaseObserver.ExceptionReason.CONNECT_TIMEOUT;
import static com.pfl.common.utils.BaseObserver.ExceptionReason.PARSE_ERROR;
import static com.pfl.common.utils.BaseObserver.ExceptionReason.UNKNOWN_ERROR;

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

        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(PARSE_ERROR);
        } else {
            onException(UNKNOWN_ERROR);
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

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                //ToastUtils.show(R.string.connect_error, Toast.LENGTH_SHORT);
                break;
            case NOT_NETWORK:
                //ToastUtils.show(R.string.connect_error, Toast.LENGTH_SHORT);
                break;
            case CONNECT_TIMEOUT:
                // ToastUtils.show(R.string.connect_timeout, Toast.LENGTH_SHORT);
                break;
            case BAD_NETWORK:
                // ToastUtils.show(R.string.bad_network, Toast.LENGTH_SHORT);
                break;
            case PARSE_ERROR:
                //  ToastUtils.show(R.string.parse_error, Toast.LENGTH_SHORT);
                break;
            case UNKNOWN_ERROR:
            default:
                // ToastUtils.show(R.string.unknown_error, Toast.LENGTH_SHORT);
                break;
        }
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
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
