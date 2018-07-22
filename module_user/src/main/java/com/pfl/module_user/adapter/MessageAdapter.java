package com.pfl.module_user.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pfl.common.entity.module_user.MessageEntity;
import com.pfl.module_user.R;

/**
 * Created by Administrator on 2018\7\17 0017.
 */

public class MessageAdapter extends BaseQuickAdapter<MessageEntity, BaseViewHolder> {

    public MessageAdapter() {
        super(R.layout.module_user_item_message);
        setOnItemClickListener();
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageEntity item) {
        helper.setVisible(R.id.module_user_view_dot, item.isRead())
                .setText(R.id.module_user_tv_title, item.getTitle())
                .setText(R.id.module_user_tv_content, item.getMessage());
    }

    public void setOnItemClickListener() {
        super.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }
}
