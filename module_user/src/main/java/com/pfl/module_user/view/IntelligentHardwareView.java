package com.pfl.module_user.view;

import com.pfl.common.entity.base.BaseEntyty;
import com.pfl.common.entity.module_user.Device;

import java.util.List;

/**
 * Created by Administrator on 2018\6\24 0024.
 */

public interface IntelligentHardwareView {

    void onSuccess(List<BaseEntyty> deviceList);

    void onDeleteSuccess(int position);

    void onRefreshCompelete();

}
