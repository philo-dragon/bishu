package com.pfl.module_user.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pfl.common.entity.module_user.MessageBean;
import com.pfl.common.entity.module_user.MessageEntity;
import com.pfl.module_user.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2018\7\17 0017.
 */

public class MessageAdapter extends BaseQuickAdapter<MessageBean.Message, BaseViewHolder> {

    public MessageAdapter() {
        super(R.layout.module_user_item_message);
        setOnItemClickListener();
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean.Message item) {
        helper.setVisible(R.id.module_user_view_dot, item.isRead())
                .setText(R.id.module_user_tv_time, getDay(item.getCreate_ts() * 1000))
                .setText(R.id.module_user_tv_title, item.getTitle())
                .setText(R.id.module_user_tv_content, item.getContent());
    }

    public void setOnItemClickListener() {
        super.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    public String getDay(long time) {

        Calendar today = Calendar.getInstance();
        Calendar old = Calendar.getInstance();
        today.setTimeInMillis(time);

        //此处好像是去除0
        today.set(Calendar.HOUR, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        old.set(Calendar.HOUR, 0);
        old.set(Calendar.MINUTE, 0);
        old.set(Calendar.SECOND, 0);
        //老的时间减去今天的时间
        long intervalMilli = old.getTimeInMillis() - today.getTimeInMillis();
        int xcts = (int) (intervalMilli / (24 * 60 * 60 * 1000));
        // -2:前天 -1：昨天 0：今天 1：明天 2：后天， out：显示日期
        if (xcts >= -2 && xcts <= 2) {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return String.valueOf(xcts) + dateFormat.format(new Date(time));
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            return dateFormat.format(new Date(time));
        }
    }

}
