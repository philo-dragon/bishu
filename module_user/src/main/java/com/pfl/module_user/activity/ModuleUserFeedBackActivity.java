package com.pfl.module_user.activity;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityFeedBackBinding;
import com.pfl.module_user.di.module_feedback.DaggerFeedbackComponent;
import com.pfl.module_user.di.module_feedback.FeedbackModule;
import com.pfl.module_user.view.FeedbackView;
import com.pfl.module_user.viewmodel.FeedbackViewModel;

import javax.inject.Inject;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_FEED_BACK)
public class ModuleUserFeedBackActivity extends BaseActivity<ModuleUserActivityFeedBackBinding> implements FeedbackView, View.OnClickListener {

    @Inject
    FeedbackViewModel viewModel;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_feed_back;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

        DaggerFeedbackComponent
                .builder()
                .appComponent(appComponent)
                .feedbackModule(new FeedbackModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void initView() {

        RxClickUtil.RxClick(mBinding.moduleUserCvOk, this);


    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {
    }

    @Override
    public void feedbackSuccess() {
        ToastUtils.showShort("提交成功!");
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.module_user_cv_ok) {

            String content = mBinding.moduleUserEtContent.getText().toString().trim();
            if (!StringUtils.isEmpty(content)) {
                viewModel.feedback(content);
            }
        }
    }
}
