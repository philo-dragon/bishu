package com.pfl.module_user.activity;

import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.imageloader.ImageLoader;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityRegistBinding;
import com.pfl.module_user.di.module_regist.DaggerRegistComponent;
import com.pfl.module_user.di.module_regist.RegistModule;
import com.pfl.module_user.po.ModuleUserPoUser;
import com.pfl.module_user.view.RegistView;
import com.pfl.module_user.viewmodel.RegistViewModel;

import javax.inject.Inject;

/**
 * 用户注册
 */
@Route(path = RouteUtils.MODULE_USER_ACTIVITY_REGIST)
public class ModuleUserRegistActivity extends BaseActivity<ModuleUserActivityRegistBinding> implements RegistView, View.OnClickListener {

    @Inject
    ImageLoader imageLoader;

    @Override
    protected int getBackgroundColorRes() {
        return R.color.white;
    }

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_regist;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerRegistComponent
                .builder()
                .appComponent(appComponent)
                .registModule(new RegistModule(this, this))
                .build()
                .inject(this);

    }


    @Override
    public void initView() {
        /*imageLoader.loadImage(this, ImageConfigImpl.
                builder().url("http://g.hiphotos.baidu.com/image/pic/item/c8ea15ce36d3d539f09733493187e950342ab095.jpg").
                imageView(mBinding.imgUser).
                build());*/


        RxClickUtil.RxClick(mBinding.inRegistView1.moduleUserCvNext, this);
        RxClickUtil.RxClick(mBinding.inRegistView2.moduleUserCvRegist, this);
    }

    @Override
    public void setToolBar() {
    }

    @Override
    public void initData() {
    }

    @Override
    public void onSuccess(String token) {
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.module_user_cv_next) {
            mBinding.modulerUserVfFlipper.showNext();
        } else if (id == R.id.module_user_cv_regist) {

        }
    }
}
