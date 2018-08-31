package com.pfl.module_user.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.google.zxing.client.android.ZxingUtils;
import com.pfl.common.base.BaseActivity;
import com.pfl.common.di.AppComponent;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.service.ModuleUserRouteService;
import com.pfl.common.utils.BottomDialogManager;
import com.pfl.common.utils.RouteUtils;
import com.pfl.common.utils.RxClickUtil;
import com.pfl.common.utils.SocialUtil;
import com.pfl.module_user.R;
import com.pfl.module_user.databinding.ModuleUserActivityInviteFirendsBinding;

import net.arvin.socialhelper.SocialHelper;
import net.arvin.socialhelper.callback.SocialShareCallback;
import net.arvin.socialhelper.entities.QQShareEntity;
import net.arvin.socialhelper.entities.ShareEntity;
import net.arvin.socialhelper.entities.WBShareEntity;
import net.arvin.socialhelper.entities.WXShareEntity;

import java.util.ArrayList;

@Route(path = RouteUtils.MODULE_USER_ACTIVITY_INVITE_FIRENDS)
public class ModuleUserInviteFriendsActivity extends BaseActivity<ModuleUserActivityInviteFirendsBinding> implements View.OnClickListener, SocialShareCallback {

    private SocialHelper socialHelper;

    @Override
    public int getContentView() {
        return R.layout.module_user_activity_invite_firends;
    }

    @Override
    public void componentInject(AppComponent appComponent) {

    }

    @Override
    public void initView() {
        socialHelper = SocialUtil.INSTANCE.socialHelper;
        RxClickUtil.RxClick(mBinding.moduleUserCvShare, this);
        RxClickUtil.RxClick(mBinding.moduleUserTvCopy, this);

        UserInfo userInfo = ModuleUserRouteService.getUserInfo();
        mBinding.moduleUserTvQrCode.setText(userInfo.getReferral_code());
        mBinding.moduleUserImgQrCode.setImageBitmap(ZxingUtils.createBitmap(SocialUtil.APP_YYB_URL));
    }

    @Override
    public void setToolBar() {
        setToolBarHasBack(mBinding.inToolbarLayout.titleBar);
    }

    @Override
    public void initData() {

    }

    public void share() {
        BottomDialogManager.showShareDialog(getSupportFragmentManager(), this);
       /* Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);
        share_intent.setType("text/plain");
        share_intent.putExtra(Intent.EXTRA_SUBJECT, "contentTitle");
        share_intent.putExtra(Intent.EXTRA_TEXT, "content");

        share_intent = Intent.createChooser(share_intent, getApplication().getPackageName());
        startActivity(share_intent);*/
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
        } else if (id == R.id.lib_common_tv_wechat) {
            socialHelper.shareWX(this, SocialUtil.INSTANCE.createWXShareEntity(false, 3, R.drawable.lib_resource_share_default_img), this);
        } else if (id == R.id.lib_common_tv_moment) {
            socialHelper.shareWX(this, SocialUtil.INSTANCE.createWXShareEntity(true, 3, R.drawable.lib_resource_share_default_img), this);
        }/* else if (id == R.id.lib_common_tv_qq) {
            socialHelper.shareQQ(this, SocialUtil.INSTANCE.createQQShareEntity(2), this);
        } else if (id == R.id.lib_common_tv_qzone) {
            socialHelper.shareQQ(this, SocialUtil.INSTANCE.createQQShareEntity(3), this);
        } else if (id == R.id.lib_common_tv_sina) {
            socialHelper.shareWB(this, SocialUtil.INSTANCE.createWBShareEntity(2, R.mipmap.ic_launcher), this);
        }*/
    }

    //用处：qq登录和分享回调，以及微博登录回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && socialHelper != null) {//qq分享如果选择留在qq，通过home键退出，再进入app则不会有回调
            socialHelper.onActivityResult(requestCode, resultCode, data);
        }
    }

    //用处：微博分享回调
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (socialHelper != null) {
            socialHelper.onNewIntent(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socialHelper.clear();
    }

    @Override
    public void shareSuccess(int type) {

    }

    @Override
    public void socialError(String msg) {
        ToastUtils.showShort(msg);
    }
}
