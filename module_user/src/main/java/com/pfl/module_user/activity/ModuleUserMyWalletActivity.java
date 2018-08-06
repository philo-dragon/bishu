package com.pfl.module_user.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.base.MultiTypeAdapter;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.ScoreLog;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.utils.App;
import com.pfl.common.utils.BaseObserver;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.TitleBar;
import com.pfl.module_user.R;
import com.pfl.module_user.adapter.WalletAdapter;
import com.pfl.module_user.databinding.ModuleUserActivityMyWalletBinding;
import com.pfl.module_user.di.module_wallet.DaggerWalletComponent;
import com.pfl.module_user.di.module_wallet.WalletModule;
import com.pfl.module_user.view.WalletView;
import com.pfl.module_user.viewmodel.WalletViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import static android.widget.LinearLayout.VERTICAL;

/**
 * 我的钱包
 */
@Route(path = RouteUtils.MODULE_USER_ACTIVITY_MY_WALLET)
public class ModuleUserMyWalletActivity extends BaseActivity<ModuleUserActivityMyWalletBinding> implements WalletView, View.OnClickListener {

    @Inject
    ImageLoader imageLoader;
    @Inject
    WalletViewModel viewModel;

    @Autowired
    String score;
    private WalletAdapter multiTypeAdapter;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_my_wallet;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerWalletComponent
                .builder()
                .appComponent(appComponent)
                .walletModule(new WalletModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        mBinding.setScore(score);
        setRecyclerView();
        setRefreshView();
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
        mBinding.inToolbarLayout.titleBar.addAction(new TitleBar.TextAction("积分商城") {
            @Override
            public void performAction(View view) {
                //Toast.makeText(App.getInstance(), "积分商城", Toast.LENGTH_SHORT).show();
                Map<String,String> paramMap = new HashMap<>();
                paramMap.put("mUrl","http://www.baidu.com");
                RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_H5,paramMap);
            }
        });
    }

    @Override
    public void initData() {
        mBinding.moduleRefreshLayout.commonRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mBinding.moduleRefreshLayout.commonRefreshLayout.autoRefresh(0);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private void setRecyclerView() {

        multiTypeAdapter = new WalletAdapter();
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
    public void onSuccess(boolean isRefresh, List<ScoreLog.Wallet> items) {
        if (isRefresh) {
            multiTypeAdapter.setNewData(items);
        } else {
            if (!items.isEmpty()) {
                multiTypeAdapter.addData(items);
            }
        }
    }
}
