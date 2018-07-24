package com.pfl.module_user.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityInviteFirendsBinding;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INVITE_FIRENDS)
public class ModuleUserInviteFirendsActivity extends BaseActivity<ModuleUserActivityInviteFirendsBinding> implements View.OnClickListener {

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_invite_firends;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {

        RxClickUtil.RxClick(mBinding.moduleUserCvShare, this);
        RxClickUtil.RxClick(mBinding.moduleUserTvCopy, this);


    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {

    }

    public void share() {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);
        share_intent.setType("text/plain");
        share_intent.putExtra(Intent.EXTRA_SUBJECT, "contentTitle");
        share_intent.putExtra(Intent.EXTRA_TEXT, "content");

        share_intent = Intent.createChooser(share_intent, getApplication().getPackageName());
        startActivity(share_intent);
    }

    public void copy() {
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(mBinding.moduleUserTvQrCode.getText().toString());
        Toast.makeText(this, "复制成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.module_user_cv_share) {
            share();
        } else if (id == R.id.module_user_tv_copy) {
            copy();
        }
    }
}
