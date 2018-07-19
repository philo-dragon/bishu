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
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.utils.App;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.CommonHeader;
import com.pfl.common.weidget.TitleBar;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityMyWalletBinding;
import com.pfl.module_user.di.module_wallet.DaggerWalletComponent;
import com.pfl.module_user.di.module_wallet.WalletModule;
import com.pfl.module_user.view.WalletView;
import com.pfl.module_user.viewmodel.WalletViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

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
    private CommonHeader commonHeader;
    private MultiTypeAdapter multiTypeAdapter;

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
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
        setRecyclerView();
        setRefreshView();

        mBinding.inToolbarLayout.titleBar.addAction(new TitleBar.TextAction("积分商城") {
            @Override
            public void performAction(View view) {
                Toast.makeText(App.getInstance(), "积分商城", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        mBinding.moduleRefreshLayout.commonRefreshLayout.autoRefresh(0);
    }

    @Override
    public void onClick(View v) {

    }

    private void setRecyclerView() {

        commonHeader = new CommonHeader(mContext);
        mBinding.moduleRefreshLayout.commonRefreshLayout.setRefreshHeader(commonHeader);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mBinding.moduleRefreshLayout.commonRecyclerView.setLayoutManager(layoutManager);
        mBinding.moduleRefreshLayout.commonRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, VERTICAL));
        multiTypeAdapter = new MultiTypeAdapter();
        mBinding.moduleRefreshLayout.commonRecyclerView.setAdapter(multiTypeAdapter);
    }

    private void setRefreshView() {
        mBinding.moduleRefreshLayout.commonRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {

                Observable.just(1)
                        .delay(500, TimeUnit.MILLISECONDS)
                        .compose(RxSchedulers.<Integer>noCheckNetworkCompose())
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                commonHeader.getCompleteView().setVisibility(View.VISIBLE);
                                refreshlayout.finishRefresh(200);
                                viewModel.requestData();
                            }
                        });


            }
        });
        mBinding.moduleRefreshLayout.commonRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }


    @Override
    public void onSuccess(List<MultiTypeAdapter.IItem> scoreLogs) {

        multiTypeAdapter.setItems(scoreLogs);

    }
}
