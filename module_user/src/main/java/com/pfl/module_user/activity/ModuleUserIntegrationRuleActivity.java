package com.pfl.module_user.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityIntegrationRuleBinding;

/**
 * 积分规则
 */
@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INTEGRATION_RULE)
public class ModuleUserIntegrationRuleActivity extends BaseActivity<ModuleUserActivityIntegrationRuleBinding>{

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_integration_rule;
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
}
