package com.pfl.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.pfl.common.BuildConfig;
import com.pfl.common.di.AppComponent;
import com.pfl.common.di.AppModule;
import com.pfl.common.di.DaggerAppComponent;
import com.pfl.common.di.NetworkModule;
import com.pfl.common.utils.App;
import com.pfl.common.utils.AppManager;
import com.pfl.common.utils.BaseUrlManager;
import com.pfl.common.utils.CallBacks;
import com.pfl.common.utils.CrashHandler;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class BaseApplication extends Application {

    private AppComponent appComponent;

    /**
     * 如果你使用了LayoutInflater.from(getApplicationContext())或者LayoutInflater.from(getApplication())
     * 就需要一下操作，如果没有，以下方法不必重写
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(/*InflaterAuto.wrap(*/base/*)*/);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
        initRouter();//初始化Router
        initBaseUrl();
        registerLifecycleCallbacks();//注册Activity生命周期监听
        initAppComponent();//Dagger2 初始化全局参数
        Utils.init(App.getInstance());
    }

    protected void initBaseUrl() {
        BaseUrlManager.init("http://apitest.topzuqiu.cn/", "http://apitest.topzuqiu.cn/", false);//动态切换BaseUrl
    }

    protected void initAppComponent() {

        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .networkModule(new NetworkModule())
                    .build();
        }

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initRouter() {

        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();//开启调试模式（如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭，否则有安全隐患）
        }

        ARouter.init(this);
    }

    private void registerLifecycleCallbacks() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            registerActivityLifecycleCallbacks(new CallBacks() {
                @Override
                public void onActivityCreated(final Activity activity, Bundle savedInstanceState) {
                    AppManager.getAppManager().addActivity(activity);
                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    AppManager.getAppManager().finishActivity(activity);
                }
            });
        }
    }

}

