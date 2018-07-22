package com.pfl.module_user.view;

import com.pfl.common.base.MultiTypeAdapter;
import com.pfl.common.entity.module_user.MineTrip;
import com.pfl.common.utils.BaseObserver;

import java.util.List;

/**
 * Created by rocky on 2018/1/2.
 */

public interface MyTripView {

    void onRefreshComplete(boolean isEnabledLoadmore);

    void onLoadmoreComplete(boolean isEnabledLoadmore);

    void onFail(BaseObserver.ExceptionReason exceptionReason);

    void onSuccess(boolean isRefresh, List<MineTrip.MineTripBean> list);

}
