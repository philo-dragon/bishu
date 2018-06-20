package com.pfl.module_user.fragment;


import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseFragment;
import com.pfl.common.base.MultiTypeAdapter;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.App;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.weidget.CommonHeader;
import com.pfl.common.weidget.TitleBar;
import com.pfl.module_user.BR;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserFragmentMineTripBinding;
import com.pfl.module_user.di.module_my_trip.DaggerMyTtipComponent;
import com.pfl.module_user.di.module_my_trip.MyTripModule;
import com.pfl.module_user.view.MyTripView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

/**
 * 我的行程
 */
@Route(path = RouteUtils.MODULE_USER_FRAGMENT_MINE_TRIP)
public class ModuleUserMineTripFragment extends BaseFragment<ModuleUserFragmentMineTripBinding> implements MyTripView, View.OnClickListener {

    private MultiTypeAdapter multiTypeAdapter;
    private CommonHeader commonHeader;

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
        setRecyclerView();
        setRefreshView();
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

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        commonHeader.getCompleteView().setVisibility(View.VISIBLE);
                        refreshlayout.finishRefresh(500);

                    }
                }, 2000);

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
    public void initData() {
        multiTypeAdapter.setItems(getData());
        multiTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }


    public List<MultiTypeAdapter.IItem> getData() {

        List<MultiTypeAdapter.IItem> tripBeans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            MineTripBean tripBean = new MineTripBean();
            tripBean.setStartTime("2018-05-25 16:12");
            tripBean.setEndTime("2018-05-26 13:54");
            tripBean.setMoney(i % 2 == 0 ? "+5" : "0");
            tripBean.setName(i % 2 == 0 ? "已售行程" : "待售行程");
            tripBean.setType(i % 2 == 0 ? 1 : 0);
            tripBeans.add(tripBean);
        }

        return tripBeans;
    }

    @Override
    public void onSuccess() {

    }

    public static class MineTripBean implements MultiTypeAdapter.IItem {
        private String name;
        private String startTime;
        private String endTime;
        private String money;
        private int type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public int getLayout() {
            return R.layout.module_user_item_my_trip;
        }

        @Override
        public int getVariableId() {
            return BR.item;
        }
    }
}
