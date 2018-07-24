package com.bishu.app;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pfl.common.base.BaseApplication;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.umeng.commonsdk.UMConfigure;

/**
 * Created by Administrator on 2018\7\7 0007.
 */

public class BiShuApp extends BaseApplication {

    private String app_dev_url = "http://47.93.101.221:10156/";
    private String app_release_url = "http://47.93.101.221:10156/";
    private String app_api_sersion = "v1";

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new MaterialHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，
         也需要在App代码中调用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         UMConfigure.init调用中appkey和channel参数请置为null）。
         */
        UMConfigure.init(this, "5b574a098f4a9d39370000a8", "BiShu", UMConfigure.DEVICE_TYPE_PHONE, null);
    }

    @Override
    protected void initBaseUrl(String dev_url, String release_url, String api_sersion, boolean isDebug) {
        super.initBaseUrl(app_dev_url, app_release_url, app_api_sersion, BuildConfig.DEBUG);
    }
}
