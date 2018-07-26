package com.pfl.common.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.zxing.client.android.BaseCaptureActivity;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.pfl.common.R;
import com.pfl.common.listener.IActivity;
import com.pfl.common.utils.App;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.common.utils.TitleBarUtil;
import com.pfl.common.weidget.TitleBar;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.umeng.analytics.MobclickAgent;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by rocky on 2018/4/2.
 */

public abstract class BaseScanActivity<T extends ViewDataBinding> extends BaseCaptureActivity implements IActivity,LifecycleProvider<ActivityEvent> {

    protected T mBinding;
    protected Activity mContext;

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.mContext = this;
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper
                .getCurrentPage(this)
                .setSwipeBackEnable(isSwipeBackEnable());

        setContentView();
        StatusBarUtil.immersive(this);
        drakMode();
        ARouter.getInstance().inject(this);

        componentInject(App.getInstance(BaseApplication.class).getAppComponent());
        initView();
        setToolBar();
        initData();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        MobclickAgent.onPause(this);
    }

    @Override
    @CallSuper
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }


    private void setContentView() {
        if (isSupportDataBinding()) {
            mBinding = DataBindingUtil.setContentView(this, getContentView());
            mBinding.getRoot().setBackgroundResource(getBackgroundColorRes());
        } else {
            setContentView(getContentView());
            getWindow().getDecorView().setBackgroundResource(getBackgroundColorRes());
        }
    }

    /**
     * 改变状态栏文字 图标颜色
     *
     * @return
     */
    private void drakMode() {
        if (isDrakMode()) {
            StatusBarUtil.darkMode(this, true);
        } else {
            StatusBarUtil.darkMode(this, false);
        }
    }

    /**
     * 控制状态栏文字 图标颜色
     *
     * @return
     */
    protected boolean isDrakMode() {
        return true;
    }

    /**
     * 设置app默认背景色
     *
     * @return
     */
    protected int getBackgroundColorRes() {
        return R.color.lib_resource_background;
    }

    /**
     * 是否支持右滑退出
     *
     * @return
     */
    protected boolean isSwipeBackEnable() {
        return true;
    }

    /**
     * 设置标题 带返回按钮
     *
     * @param titleBar
     */
    protected void setToolBarHasBack(TitleBar titleBar) {
        TitleBarUtil.setToolBarHasBack(titleBar, getTitle().toString());
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 设置标题 不带返回按钮
     *
     * @param titleBar
     */
    protected void setToolBarNoBack(TitleBar titleBar) {
        TitleBarUtil.setToolBarNoBack(titleBar, getTitle().toString());
    }

    /**
     * 是否使用Databinding
     *
     * @return
     */
    private boolean isSupportDataBinding() {
        return true;
    }

}
