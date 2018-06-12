package com.pfl.common.http;

import java.lang.reflect.Proxy;

public class RetrofitFactory {

    private static RetrofitFactory INSTANCE;

    private RetrofitFactory() {
    }

    public static RetrofitFactory getInstance() {
        if (null == INSTANCE) {
            synchronized (RetrofitFactory.class) {
                if (null == INSTANCE) {
                    INSTANCE = new RetrofitFactory();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * @param tClass         请求数据类型接口Service
     * @param currentService 请求数据真实接口Service
     * @param tokenService   请求Token接口 Service
     * @param <T>            返回代理请求数据类型接口
     * @return
     */
    public <T> T getProxy(Class<T> tClass, Object currentService, RetrofitService tokenService) {
        T t = (T) currentService;
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class<?>[]{tClass}, new ProxyHandler(t, tokenService));
    }

}