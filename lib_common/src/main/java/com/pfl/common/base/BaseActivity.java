package com.pfl.common.base;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.pfl.common.R;
import com.pfl.common.listener.IActivity;
import com.pfl.common.utils.App;
import com.pfl.common.utils.StatusBarUtil;
import com.pfl.common.utils.TitleBarUtil;
import com.pfl.common.weidget.TitleBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by rocky on 2018/4/2.
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends RxAppCompatActivity implements IActivity {

    protected T mBinding;
    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.mContext = this;
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(isSwipeBackEnable());
        setContentView();
        drakMode();//设置状态栏文字图标颜色
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
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    protected int getBackgroundColorRes() {
        return R.color.lib_resource_background;
    }

    private void setContentView() {
        if (isSupportDataBinding()) {
            mBinding = DataBindingUtil.setContentView(this, getContentView());
            mBinding.getRoot().setBackgroundResource(getBackgroundColorRes());
        } else {
            setContentView(getContentView());
        }
    }

    private void drakMode() {
        if (isDrakMode()) {
            StatusBarUtil.darkMode(this, true);
        } else {
            StatusBarUtil.darkMode(this, false);
        }
    }

    protected boolean isDrakMode() {
        return true;
    }

    protected boolean isSwipeBackEnable() {
        return true;
    }

    protected void setToolBarHasBack(TitleBar titleBar) {
        TitleBarUtil.setToolBarHasBack(titleBar, getTitle().toString());
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    protected void setToolBarNoBack(TitleBar titleBar) {
        TitleBarUtil.setToolBarNoBack(titleBar, getTitle().toString());
    }

    private boolean isSupportDataBinding() {
        return true;
    }

}
