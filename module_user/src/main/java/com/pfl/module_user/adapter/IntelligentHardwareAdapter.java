package com.pfl.module_user.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pfl.common.entity.base.BaseEntyty;
import com.pfl.common.entity.module_user.Device;
import com.pfl.module_user.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018\7\23 0023.
 */

public class IntelligentHardwareAdapter extends BaseMultiItemQuickAdapter<BaseEntyty, BaseViewHolder> {
    public IntelligentHardwareAdapter() {
        super(new ArrayList<BaseEntyty>());
        addItemType(R.layout.module_user_item_add_intelligent_harddware_layout, R.layout.module_user_item_add_intelligent_harddware_layout);
        addItemType(R.layout.module_user_item_intelligent_harddware_layout, R.layout.module_user_item_intelligent_harddware_layout);
        setOnItemClickListener();
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseEntyty item) {
        if (helper.getItemViewType() == R.layout.module_user_item_intelligent_harddware_layout) {
            Device.DeviceBean carLicenceBean = (Device.DeviceBean) item;
            helper.setText(R.id.module_user_tv_name, carLicenceBean.getName());
            helper.getView(R.id.module_user_img_hardware_action).setTag(mData.indexOf(item));
            helper.getView(R.id.module_user_img_hardware_action).setOnClickListener(showDialog);
        }
    }

    public void setOnItemClickListener() {
        super.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Device.DeviceBean carLicenceBean = (Device.DeviceBean) mData.get(position);
                if (carLicenceBean.getItemType() == R.layout.module_user_item_add_intelligent_harddware_layout) {
                    if (mOnAddIntelligentHardwareActionListener != null) {
                        mOnAddIntelligentHardwareActionListener.onAddIntelligentHardware();
                    }
                }
            }
        });
    }

    View.OnClickListener showDialog = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (mOnShowHardwareActionListener != null) {
                int position = (int) v.getTag();
                mOnShowHardwareActionListener.onShowHardwareAction(position);
            }

        }
    };

    private OnAddIntelligentHardwareActionListener mOnAddIntelligentHardwareActionListener;
    private OnShowHardwareActionListener mOnShowHardwareActionListener;

    public void setOnAddIntelligentHardwareActionListener(OnAddIntelligentHardwareActionListener onAddIntelligentHardwareActionListener) {
        this.mOnAddIntelligentHardwareActionListener = onAddIntelligentHardwareActionListener;
    }

    public void setOnShowHardwareActionListener(OnShowHardwareActionListener onShowHardwareActionListener) {
        this.mOnShowHardwareActionListener = onShowHardwareActionListener;
    }

    public interface OnAddIntelligentHardwareActionListener {
        void onAddIntelligentHardware();
    }

    public interface OnShowHardwareActionListener {
        void onShowHardwareAction(int position);
    }

}
