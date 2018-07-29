package com.pfl.module_user.activity;

import android.Manifest;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.Device;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.PermissionUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityIntelligentHardwareListBinding;
import com.pfl.module_user.databinding.ModuleUserActivityIntelligentHardwareManagerBinding;
import com.pfl.module_user.di.module_intelligent_hardware.DaggerIntelligentHardwareComponent;
import com.pfl.module_user.di.module_intelligent_hardware.IntelligentHardwareModule;
import com.pfl.module_user.view.IntelligentHardwareView;
import com.pfl.module_user.viewmodel.IntelligentHardwareViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import me.weyye.hipermission.PermissionItem;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INTELLIGENT_HARDWARE_LIST)
public class ModuleUserIntelligentHardwareListActivity extends BaseActivity<ModuleUserActivityIntelligentHardwareListBinding> implements IntelligentHardwareView, View.OnClickListener {

    @Inject
    IntelligentHardwareViewModel viewModel;

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
        RxClickUtil.RxClick(mBinding.moduleUserCvAdasInfo, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvJingInfo, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvAddDevice, this);
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {
        viewModel.getDevices("");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.module_user_cv_adas_info) {
            BottomDialogManager.unBindDialog(getSupportFragmentManager(), this);
        } else if (id == R.id.module_user_cv_jing_info) {
            BottomDialogManager.unBindDialog(getSupportFragmentManager(), this);
        } else if (id == R.id.module_user_cv_add_device) {
            requestPermission();
        }
    }

    @Override
    public void onSuccess(List<Device.DeviceBean> deviceList) {

    }

    @Override
    public void onAddSuccess() {

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
}
