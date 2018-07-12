package com.pfl.module_user.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.base.BaseEvent;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.EventBusUtil;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityInitialValueBinding;
import com.pfl.module_user.di.module_initialvalue.DaggerInitialValueComponent;
import com.pfl.module_user.di.module_initialvalue.InitialValueModule;
import com.pfl.module_user.view.InitialValueView;
import com.pfl.module_user.viewmodel.InitialValueViewModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INITIAL_VALUE)
public class ModuleUserInitialValueActivity extends BaseActivity<ModuleUserActivityInitialValueBinding> implements InitialValueView, View.OnClickListener {

    @Inject
    InitialValueViewModel valueModule;

    private String REAL_NAME = "-1";

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_initial_value;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerInitialValueComponent
                .builder()
                .appComponent(appComponent)
                .initialValueModule(new InitialValueModule(this, this))
                .build()
                .inject(this);

    }

    @Override
    public void initView() {
        RxClickUtil.RxClick(mBinding.moduleUserRlNickName, this);
        RxClickUtil.RxClick(mBinding.moduleUserRlGender, this);
        RxClickUtil.RxClick(mBinding.moduleUserCvCommit, this);
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.module_user_rl_nick_name) {
            RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_INPUT_NICK_NAME);
        } else if (id == R.id.module_user_rl_gender) {
            BottomDialogManager.showGenderDialog(getSupportFragmentManager(), this);
        } else if (id == R.id.lib_common_tv_male) {
            REAL_NAME = "1";
            mBinding.moduleUserTvGender.setText("男");
        } else if (id == R.id.lib_common_tv_female) {
            REAL_NAME = "0";
            mBinding.moduleUserTvGender.setText("女");
        } else if (id == R.id.module_user_cv_commit) {
            String realName = mBinding.moduleUserTvRealName.getText().toString().trim();
            String gender = mBinding.moduleUserTvGender.getText().toString().trim();

            if (StringUtils.isEmpty(realName)) {
                ToastUtils.showShort("请填写真实姓名");
                return;
            }
            if (StringUtils.isEmpty(gender)) {
                ToastUtils.showShort("请选择性别");
                return;
            }

            valueModule.requestData(realName, REAL_NAME);
        }
    }

    @Override
    public void onSuccess() {
        ToastUtils.showShort("提交成功");
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBusUtil.regist(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBusUtil.unregist(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventBus(BaseEvent<String> event) {
        if (event != null) {
            mBinding.moduleUserTvRealName.setText(event.getT());
        }
    }

}
