package com.pfl.module_user.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.base.BaseEvent;
import com.pfl.common.utils.EventBusUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityInitialValueBinding;
import com.pfl.module_user.databinding.ModuleUserActivityInputNickNameBinding;

/**
 *
 */
@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INPUT_NICK_NAME)
public class ModuleUserInputNickNameActivity extends BaseActivity<ModuleUserActivityInputNickNameBinding> {


    @Override
    public int getContentView() {
        return R.layout.module_user_activity_input_nick_name;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {

    }

    @Override
    public void finish() {
        String trim = mBinding.moduleUserEtNickName.getText().toString().trim();
        if (!StringUtils.isEmpty(trim)) {
            BaseEvent<String> event = new BaseEvent<>();
            event.setFrom(ModuleUserInputNickNameActivity.class.getSimpleName());
            event.setT(trim);
            EventBusUtil.postMessage(event);
        }
        super.finish();
    }
}
