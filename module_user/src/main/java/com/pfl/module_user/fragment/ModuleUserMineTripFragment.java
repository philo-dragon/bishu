package com.pfl.module_user.fragment;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseLazyFragment;
import com.pfl.common.base.MultiTypeAdapter;
import com.pfl.common.di.AppComponent;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.App;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.CommonHeader;
import com.pfl.common.weidget.TitleBar;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserFragmentMineTripBinding;
import com.pfl.module_user.di.module_my_trip.DaggerMyTtipComponent;
import com.pfl.module_user.di.module_my_trip.MyTripModule;
import com.pfl.module_user.view.MyTripView;
import com.pfl.module_user.viewmodel.MyTripViewModel;
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
 * 我的行程
 */
@Route(path = RouteUtils.MODULE_USER_FRAGMENT_MINE_TRIP)
public class ModuleUserMineTripFragment extends BaseLazyFragment<ModuleUserFragmentMineTripBinding> implements MyTripView, View.OnClickListener {

    private MultiTypeAdapter multiTypeAdapter;
    private CommonHeader commonHeader;

    @Inject
    MyTripViewModel viewModel;

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
                Toast.makeText(App.getInstance(), "积分规则", Toast.LENGTH_SHORT).show();
            }
        });
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
    public void onFirstUserVisible() {
        setRecyclerView();
        setRefreshView();
        mBinding.moduleRefreshLayout.commonRefreshLayout.autoRefresh(0);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(List<MultiTypeAdapter.IItem> items) {
        multiTypeAdapter.setItems(items);
        multiTypeAdapter.notifyDataSetChanged();
    }

}
