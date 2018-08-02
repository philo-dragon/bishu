package com.pfl.module_user.activity;

import android.Manifest;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.base.BaseEntyty;
import com.pfl.common.entity.base.BaseEvent;
import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.entity.module_user.Device;
import com.pfl.common.service.ModuleUserRouteService;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.EventBusUtil;
import com.pfl.common.utils.PermissionUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.adapter.AddCarAdapter;
import com.pfl.module_user.adapter.IntelligentHardwareAdapter;
import com.pfl.module_user.databinding.ModuleUserActivityIntelligentHardwareListBinding;
import com.pfl.module_user.databinding.ModuleUserActivityIntelligentHardwareManagerBinding;
import com.pfl.module_user.di.module_intelligent_hardware.DaggerIntelligentHardwareComponent;
import com.pfl.module_user.di.module_intelligent_hardware.IntelligentHardwareModule;
import com.pfl.module_user.view.IntelligentHardwareView;
import com.pfl.module_user.viewmodel.IntelligentHardwareViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.weyye.hipermission.PermissionItem;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INTELLIGENT_HARDWARE_LIST)
public class ModuleUserIntelligentHardwareListActivity extends BaseActivity<ModuleUserActivityIntelligentHardwareListBinding>
        implements IntelligentHardwareView,
        BottomDialogManager.OnDeleteIntelligentHardwareListener,
        IntelligentHardwareAdapter.OnAddIntelligentHardwareActionListener,
        IntelligentHardwareAdapter.OnShowHardwareActionListener {

    @Inject
    IntelligentHardwareViewModel viewModel;
    private IntelligentHardwareAdapter multiTypeAdapter;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_intelligent_hardware_list;
    }

    @Override
    public void componentInject(AppComponent appComponent) {
        DaggerIntelligentHardwareComponent
                .builder()
                .appComponent(appComponent)
                .intelligentHardwareModule(new IntelligentHardwareModule(this, this))
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
        multiTypeAdapter = new IntelligentHardwareAdapter();
        multiTypeAdapter.setOnAddIntelligentHardwareActionListener(this);
        multiTypeAdapter.setOnShowHardwareActionListener(this);
        multiTypeAdapter.bindToRecyclerView(mBinding.moduleRefreshLayout.commonRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mBinding.moduleRefreshLayout.commonRecyclerView.setLayoutManager(layoutManager);
    }

    private void setRefreshView() {
        mBinding.moduleRefreshLayout.commonRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshData();
            }
        });

    }

    private void refreshData() {
        viewModel.getDevices(ModuleUserRouteService.getUser().getUid());
    }

    @Override
    public void onSuccess(List<BaseEntyty> deviceList) {
        multiTypeAdapter.setNewData(deviceList);
    }

    @Override
    public void onDeleteSuccess(int position) {
        multiTypeAdapter.getData().remove(position);
        multiTypeAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onRefreshCompelete() {
        mBinding.moduleRefreshLayout.commonRefreshLayout.finishRefresh();
        mBinding.moduleRefreshLayout.commonRefreshLayout.setEnableLoadmore(false);
    }

    private void requestPermission() {

        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "拍照权限", R.drawable.permission_ic_camera));
        permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储权限", R.drawable.permission_ic_storage));

        PermissionUtil.requestPermission(permissionItems, new PermissionUtil.SimplePermissionCallback() {

            @Override
            public void onFinish() {
                Observable.just(1).delay(100, TimeUnit.MILLISECONDS).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_ADD_HARDWARE);
                    }
                });
            }
        });
    }

    @Override
    public void onAddIntelligentHardware() {
        requestPermission();
    }

    @Override
    public void onShowHardwareAction(int position) {
        Device.DeviceBean deviceBean = (Device.DeviceBean) multiTypeAdapter.getData().get(position);
        BottomDialogManager.unBindDialog(getSupportFragmentManager(), position, deviceBean.getName(), this);
    }

    @Override
    public void onDeleteIntelligentHardware(int position) {
        Device.DeviceBean deviceBean = (Device.DeviceBean) multiTypeAdapter.getData().get(position);
        viewModel.deleteDevice(position, deviceBean.getId());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBus(BaseEvent<Device.DeviceBean> event) {
      /*  Device.DeviceBean deviceBean = event.getT();
        deviceBean.setItemType(R.layout.module_user_item_intelligent_harddware_layout);
        multiTypeAdapter.addData(deviceBean);*/
        refreshData();
    }
}
