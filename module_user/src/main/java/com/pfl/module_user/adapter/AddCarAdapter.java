package com.pfl.module_user.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pfl.common.entity.base.BaseEntyty;
import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018\7\23 0023.
 */

public class AddCarAdapter extends BaseMultiItemQuickAdapter<BaseEntyty, BaseViewHolder> {
    public AddCarAdapter() {
        super(new ArrayList<BaseEntyty>());
        addItemType(R.layout.module_user_item_car_info_layout, R.layout.module_user_item_car_info_layout);
        addItemType(R.layout.module_user_item_add_car_layout, R.layout.module_user_item_add_car_layout);
        setOnItemClickListener();
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseEntyty item) {
        if (helper.getItemViewType() == R.layout.module_user_item_car_info_layout) {
            CarLicence.CarLicenceBean carLicenceBean = (CarLicence.CarLicenceBean) item;
            helper.setText(R.id.module_user_tv_car_no, carLicenceBean.getPlate_number());
        }
    }

    public void setOnItemClickListener() {
        super.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CarLicence.CarLicenceBean carLicenceBean = (CarLicence.CarLicenceBean) mData.get(position);
                if (carLicenceBean.getItemType() == R.layout.module_user_item_car_info_layout) {
                    Map<String, String> paramMap = new HashMap();
                    paramMap.put("id", carLicenceBean.getId());
                    RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_BOOK_RESULT, paramMap);
                } else if (carLicenceBean.getItemType() == R.layout.module_user_item_add_car_layout) {
                    RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_UPLOAD_DRIVING_BOOK);
                }
            }
        });
    }

}
