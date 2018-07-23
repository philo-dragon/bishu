package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.UserInfo;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.App;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.R;
import com.pfl.module_user.view.MyCenterView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

/**
 * Created by rocky on 2018/4/9.
 */

public class MyCenterViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private MyCenterView view;


    public MyCenterViewModel(LifecycleProvider lifecycle, RetrofitService service, MyCenterView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData(String uid) {
        service
                .userInfo("get", uid)
                .compose(RxSchedulers.<HttpResponse<UserInfo>>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<UserInfo>>() {
                    @Override
                    public void onSuccess(HttpResponse<UserInfo> response) {
                        view.onSuccess(response.getData());
                    }
                });
    }

    /**
     * 手机号码中间4位替换成*号
     *
     * @param mobile
     * @return
     */
    public String replaceMiddlePhone(String mobile) {
        if (mobile != null && mobile.length() == 11) {
            String phoneNumber = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            return phoneNumber;
        }
        return "";
    }

    /**
     * 认证结果
     *
     * @param state
     * @return
     */
    public String getAuthResult(int state) {
        //{0, 1, 2, 3, 4},分别表示{未认证, 认证中，认证成功，认证失败, 重复认证}|
        String result = "";
        switch (state) {
            case 0:
                result = "未认证";
                break;
            case 1:
                result = "认证中";
                break;
            case 2:
                result = "认证成功";
                break;
            case 3:
                result = "认证失败";
                break;
            case 4:
                result = "重复认证";
                break;
        }

        return result;
    }

    /**
     * 根据认证结果显示不同颜色
     *
     * @param state
     * @return
     */
    public int getTextColor(int state) {
        //{0, 1, 2, 3, 4},分别表示{未认证, 认证中，认证成功，认证失败, 重复认证}|
        int result = App.getInstance().getResources().getColor(R.color.module_user_mine_unverified);
        switch (state) {
            case 0:
                result = App.getInstance().getResources().getColor(R.color.module_user_mine_unverified);
                break;
            case 1:
                result = App.getInstance().getResources().getColor(R.color.module_user_mine_unverified);
                break;
            case 2:
                result = App.getInstance().getResources().getColor(R.color.module_user_mine_verified);
                break;
            case 3:
                result = App.getInstance().getResources().getColor(R.color.module_user_mine_unverified);
                break;
            case 4:
                result = App.getInstance().getResources().getColor(R.color.module_user_mine_unverified);
                break;
        }

        return result;
    }

}
