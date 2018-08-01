package com.pfl.module_user.view;

import com.pfl.common.entity.module_user.MessageBean;
import com.pfl.common.entity.module_user.MineTrip;
import com.pfl.common.utils.BaseObserver;

import java.util.List;

/**
 * Created by Administrator on 2018\7\22 0022.
 */

public interface MessageView {

    void onRefreshComplete(boolean isEnabledLoadmore);

    void onLoadmoreComplete(boolean isEnabledLoadmore);

    void onFail(BaseObserver.ExceptionReason exceptionReason);

    void onSuccess(boolean isRefresh, List<MessageBean.Message> list);
}
