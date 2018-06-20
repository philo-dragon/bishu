package com.pfl.module_user.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityFeedBackBinding;
import com.pfl.module_user.di.module_feedback.DaggerFeedbackComponent;
import com.pfl.module_user.di.module_feedback.FeedbackModule;
import com.pfl.module_user.view.FeedbackView;
import com.pfl.module_user.viewmodel.FeedbackViewModel;

import javax.inject.Inject;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_FEED_BACK)
public class ModuleUserFeedBackActivity extends BaseActivity<ModuleUserActivityFeedBackBinding> implements FeedbackView {

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

    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {
        viewModel.requestData();
    }

    @Override
    public void feedbackSuccess() {

    }
}
