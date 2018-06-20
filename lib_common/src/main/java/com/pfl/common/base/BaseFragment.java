package com.pfl.common.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.pfl.common.R;
import com.pfl.common.listener.IActivity;
import com.pfl.common.utils.App;
import com.pfl.common.utils.TitleBarUtil;
import com.pfl.common.weidget.TitleBar;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by rocky on 2018/4/12.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends RxFragment implements IActivity {

    protected T mBinding;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (isSupportDataBinding()) {
            mBinding = DataBindingUtil.inflate(inflater, getContentView(), container, false);
            return mBinding.getRoot();
        } else {
            return inflater.inflate(getContentView(), container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ARouter.getInstance().inject(this);
        componentInject(App.getInstance(BaseApplication.class).getAppComponent());
        initView();
        initData();
    }

    private boolean isSupportDataBinding() {
        return true;
    }

    protected void setToolBarHasBack(TitleBar titleBar, String title) {
        TitleBarUtil.setToolBarHasBack(titleBar, title);

    }

    protected void setToolBarNoBack(TitleBar titleBar, String title) {
        TitleBarUtil.setToolBarNoBack(titleBar, title);
    }
}
