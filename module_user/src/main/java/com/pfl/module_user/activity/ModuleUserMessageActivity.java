package com.pfl.module_user.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.MessageBean;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.common.utils.RouteUtils;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_MESSAGE)
public class ModuleUserMessageActivity extends BaseActivity<ModuleUserActivityMessageBinding> implements MessageView {

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
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
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

    private void setRecyclerView() {
        messageAdapter = new MessageAdapter();
        messageAdapter.bindToRecyclerView(mBinding.moduleRefreshLayout.commonRecyclerView);
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
                messageAdapter.setEmptyView(R.layout.lib_common_empty_layout);
                break;
        }
    }

    @Override
    public void onSuccess(boolean isRefresh, List<MessageBean.Message> items) {
        if (isRefresh) {
            messageAdapter.setNewData(items);
        } else {
            if (items.isEmpty()) {
                messageAdapter.addData(items);
            }
        }
    }
}
