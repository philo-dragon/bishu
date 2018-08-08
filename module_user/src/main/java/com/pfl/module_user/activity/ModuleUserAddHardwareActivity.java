package com.pfl.module_user.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.Result;
import com.google.zxing.client.android.AutoScannerView;
import com.google.zxing.client.android.BaseCaptureActivity;
import com.pfl.common.base.BaseScanActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.base.BaseEvent;
import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.entity.module_user.Device;
import com.pfl.common.utils.EventBusUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityAddHardwareBinding;
import com.pfl.module_user.di.module_user_add_hardware.AddHardwareModule;
import com.pfl.module_user.di.module_user_add_hardware.DaggerAddHardwareComponent;
import com.pfl.module_user.view.AddHardwareView;
import com.pfl.module_user.viewmodel.AddHardwareViewModel;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_ADD_HARDWARE)
public class ModuleUserAddHardwareActivity extends BaseScanActivity<ModuleUserActivityAddHardwareBinding> implements AddHardwareView {

    @Inject
    Gson gson;
    @Inject
    AddHardwareViewModel viewModel;

    @Override
    protected boolean isDrakMode() {
        return false;
    }

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_add_hardware;
    }

    @Override
    public void componentInject(AppComponent appComponent) {
        DaggerAddHardwareComponent
                .builder()
                .appComponent(appComponent)
                .addHardwareModule(new AddHardwareModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setToolBar() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.autoscannerView.setCameraManager(cameraManager);
    }

    @Override
    public SurfaceView getSurfaceView() {
        return mBinding.previewView;
    }

    /**
     * //{"name","行车记录仪","imei":"xxxxxx"}
     * //        对此次扫描结果不满意可以调用
     * //        reScan();
     *
     * @param rawResult
     * @param barcode
     * @param scaleFactor
     */
    @Override
    public void dealDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        try {
            playBeepSoundAndVibrate(true, false);
            String result = rawResult.getText();
            Device.DeviceBean deviceBean = gson.fromJson(result, new TypeToken<Device.DeviceBean>() {
            }.getType());
            viewModel.addDevice(deviceBean.getImei(), deviceBean.getName());
        } catch (Exception e) {
            e.printStackTrace();
            reScan();
        }
    }

    @Override
    public void onSuccess() {
        BaseEvent<Device.DeviceBean> baseEvent = new BaseEvent();
        EventBusUtil.postMessage(baseEvent);
        finish();
    }

    @Override
    public void onFail() {
        reScan();
    }
}
