package com.pfl.module_user.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.weidget.CommonHeader;
import com.pfl.module_user.R;
import com.pfl.module_user.adapter.MessageAdapter;
import com.pfl.module_user.databinding.ModuleUserActivityMessageBinding;
import com.pfl.module_user.di.module_message.DaggerMessageComponent;
import com.pfl.module_user.di.module_message.MessageModule;
import com.pfl.module_user.view.MessageView;
import com.pfl.module_user.viewmodel.MessageViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ModuleUserMessageActivity extends BaseActivity<ModuleUserActivityMessageBinding> implements MessageView {

    private CommonHeader commonHeader;

    @Inject
    MessageViewModel viewModel;
    @Inject
    MessageAdapter messageAdapter;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_message;
    }

    @Override
    public void componentInject(AppComponent appComponent) {
        DaggerMessageComponent
                .builder()
                .appComponent(appComponent)
                .messageModule(new MessageModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {
        setRecyclerView();
        setRefreshView();
    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initData() {
        mBinding.moduleRefreshLayout.commonRefreshLayout.autoRefresh(0);
    }

    private void setRecyclerView() {
        commonHeader = new CommonHeader(mContext);
        mBinding.moduleRefreshLayout.commonRefreshLayout.setRefreshHeader(commonHeader);
        messageAdapter.bindToRecyclerView(mBinding.moduleRefreshLayout.commonRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mBinding.moduleRefreshLayout.commonRecyclerView.setLayoutManager(layoutManager);
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
}
