package com.pfl.module_user.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.LazyLoadBaseFragment;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.MineTrip;
import com.pfl.common.utils.App;
import com.pfl.common.utils.BaseObserver;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.TitleBar;
import com.pfl.module_user.R;
import com.pfl.module_user.adapter.MyTripAdapter;
import com.pfl.module_user.databinding.ModuleUserFragmentMineTripBinding;
import com.pfl.module_user.di.module_my_trip.DaggerMyTtipComponent;
import com.pfl.module_user.di.module_my_trip.MyTripModule;
import com.pfl.module_user.view.MyTripView;
import com.pfl.module_user.viewmodel.MyTripViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * 我的行程
 */
@Route(path = RouteUtils.MODULE_USER_FRAGMENT_MINE_TRIP)
public class ModuleUserMineTripFragment extends LazyLoadBaseFragment<ModuleUserFragmentMineTripBinding> implements MyTripView {

    @Inject
    MyTripViewModel viewModel;
    @Inject
    MyTripAdapter multiTypeAdapter;

    @Override
    public int getContentView() {
        return R.layout.module_user_fragment_mine_trip;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerMyTtipComponent
                .builder()
                .appComponent(appComponent)
                .myTripModule(new MyTripModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        setToolBar();
    }

    @Override
    public void setToolBar() {

        setToolBarNoBack(mBinding.inToolbarLayout.titleBar, getResources().getString(R.string.module_user_my_trip));
        mBinding.inToolbarLayout.titleBar.addAction(new TitleBar.TextAction("积分规则") {
            @Override
            public void performAction(View view) {
                RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_INTEGRATION_RULE);
               /* Map<String,String> paramMap = new HashMap<>();
                paramMap.put("mUrl","http://www.baidu.com");
                RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_H5,paramMap);*/
            }
        });
    }

    private void setRecyclerView() {

        multiTypeAdapter.bindToRecyclerView(mBinding.moduleRefreshLayout.commonRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mBinding.moduleRefreshLayout.commonRecyclerView.setLayoutManager(layoutManager);
    }

    private void setRefreshView() {
        mBinding.moduleRefreshLayout.commonRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                viewModel.refreshData();
            }
        });
        mBinding.moduleRefreshLayout.commonRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                viewModel.loadmoreData();
            }
        });
    }

    @Override
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        setRecyclerView();
        setRefreshView();
        mBinding.moduleRefreshLayout.commonRefreshLayout.autoRefresh(0);
    }

    @Override
    public void onRefreshComplete(boolean isEnabledLoadmore) {
        mBinding.moduleRefreshLayout.commonRefreshLayout.finishRefresh();
        mBinding.moduleRefreshLayout.commonRefreshLayout.setEnableLoadmore(isEnabledLoadmore);
    }

    @Override
    public void onLoadmoreComplete(boolean isEnabledLoadmore) {
        mBinding.moduleRefreshLayout.commonRefreshLayout.finishLoadmore();
        mBinding.moduleRefreshLayout.commonRefreshLayout.setEnableLoadmore(isEnabledLoadmore);
    }

    @Override
    public void onFail(BaseObserver.ExceptionReason exceptionReason) {

        switch (exceptionReason) {
            case EMPTY_DATA:
                multiTypeAdapter.setEmptyView(R.layout.lib_common_empty_layout);
                break;
        }

    }


    @Override
    public void onSuccess(boolean isRefresh, List<MineTrip.MineTripBean> items) {
        if (isRefresh) {
            multiTypeAdapter.setNewData(items);
        } else {
            if (!items.isEmpty()) {
                multiTypeAdapter.addData(items);
            }
        }
    }

}
