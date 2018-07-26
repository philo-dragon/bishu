package com.pfl.module_user.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.zxing.Result;
import com.google.zxing.client.android.AutoScannerView;
import com.google.zxing.client.android.BaseCaptureActivity;
import com.pfl.common.base.BaseScanActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityAddHardwareBinding;
import com.trello.rxlifecycle2.LifecycleTransformer;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_ADD_HARDWARE)
public class ModuleUserAddHardwareActivity extends BaseScanActivity<ModuleUserActivityAddHardwareBinding> {

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

    @Override
    public void dealDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        playBeepSoundAndVibrate(true, false);
        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_LONG).show();
        //        对此次扫描结果不满意可以调用
        //        reScan();
    }

}
