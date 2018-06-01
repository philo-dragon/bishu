package com.pfl.module_user.fragment;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.pfl.common.base.BaseFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.AnimationManager;
import com.pfl.common.utils.App;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.TitleBar;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserFragmentHomeBinding;
import com.pfl.module_user.databinding.ModuleUserFragmentMineTripBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的行程
 */
@Route(path = RouteUtils.MODULE_USER_FRAGMENT_HOME)
public class ModuleUserHomeFragment extends BaseFragment<ModuleUserFragmentHomeBinding> implements View.OnClickListener {


    @Override
    public int getContentView() {
        return R.layout.module_user_fragment_home;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        setToolBar();
    }

    @Override
    public void setToolBar() {
        mBinding.inToolbarLayout.titleBar.setTitle("比数智能出行");
        mBinding.inToolbarLayout.titleBar.setTitleColor(R.color.title_color);
        ImageView tvMesage = (ImageView) mBinding.inToolbarLayout.titleBar.addAction(new TitleBar.ImageAction(R.drawable.module_user_ic_home_message) {
            @Override
            public void performAction(View view) {
                //Toast.makeText(MainActivity.this, "点击了收藏", Toast.LENGTH_SHORT).show();
                //mCollectView.setImageResource(R.mipmap.fabu);
            }
        });
        tvMesage.getLayoutParams().width = ConvertUtils.dp2px(30);
        tvMesage.getLayoutParams().height = ConvertUtils.dp2px(40);

    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onResume() {
        super.onResume();
        AnimationManager.rotateAnim(mBinding.moduleUserImgRotate, 10 * 1000);
    }

    @Override
    public void onPause() {
        super.onPause();
        AnimationManager.cancelAnim(mBinding.moduleUserImgRotate);
    }
}
