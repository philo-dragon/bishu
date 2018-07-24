package com.pfl.module_user.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.base.BaseEntyty;
import com.pfl.common.entity.base.BaseEvent;
import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.utils.EventBusUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.adapter.AddCarAdapter;
import com.pfl.module_user.databinding.ModuleUserActivityAddCarBinding;
import com.pfl.module_user.di.module_add_car.AddCarModule;
import com.pfl.module_user.di.module_add_car.DaggerAddCarComponent;
import com.pfl.module_user.view.AddCarView;
import com.pfl.module_user.viewmodel.AddCarViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_ADD_CAR)
public class ModuleUserAddCarActivity extends BaseActivity<ModuleUserActivityAddCarBinding> implements AddCarView {

    @Inject
    AddCarViewModel viewModel;
    private AddCarAdapter multiTypeAdapter;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_add_car;
    }

    @Override
    public void componentInject(AppComponent appComponent) {
        DaggerAddCarComponent
                .builder()
                .appComponent(appComponent)
                .addCarModule(new AddCarModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        EventBusUtil.regist(this);
        setRecyclerView();
        setRefreshView();
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    private void setRecyclerView() {

        multiTypeAdapter = new AddCarAdapter();
        multiTypeAdapter.bindToRecyclerView(mBinding.moduleRefreshLayout.commonRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mBinding.moduleRefreshLayout.commonRecyclerView.setLayoutManager(layoutManager);
    }

    private void setRefreshView() {
        mBinding.moduleRefreshLayout.commonRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                viewModel.getCarLicenceList();
            }
        });

    }

    @Override
    public void initData() {
        viewModel.getCarLicenceList();
    }

    @Override
    public void onSuccess(List<BaseEntyty> data) {
        multiTypeAdapter.setNewData(data);
    }

    @Override
    public void onRefreshCompelete() {
        mBinding.moduleRefreshLayout.commonRefreshLayout.finishRefresh();
        mBinding.moduleRefreshLayout.commonRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void onDestroy() {
        EventBusUtil.unregist(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBus(BaseEvent<CarLicence.CarLicenceBean> event) {
        initData();
    }
}
