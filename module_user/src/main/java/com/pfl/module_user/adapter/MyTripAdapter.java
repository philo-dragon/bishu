package com.pfl.module_user.adapter;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pfl.common.entity.module_user.MineTrip;
import com.pfl.module_user.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2018\7\17 0017.
 */

public class MyTripAdapter extends BaseQuickAdapter<MineTrip.MineTripBean, BaseViewHolder> {

    private final DateFormat dateFormat;

    public MyTripAdapter() {
        super(R.layout.module_user_item_my_trip);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    }

    @Override
    protected void convert(BaseViewHolder helper, MineTrip.MineTripBean item) {

        helper.setText(R.id.module_user_tv_money, item.getSale_status() == 0 ? "" : "+" + item.getScore_add())
                .setText(R.id.module_user_tv_name, item.getSale_status() == 0 ? "代售行程" : "已售行程")
                .setText(R.id.module_user_tv_time_start, dateFormat.format(new Date(item.getStart_ts())))
                .setText(R.id.module_user_tv_time_end, dateFormat.format(new Date(item.getStart_ts())))
                .setVisible(R.id.module_user_view_my_trip_line, mData.indexOf(item) != 0)
                .setImageResource(R.id.module_user_img_state, item.getSale_status() == 0 ? R.drawable.module_user_ic_for_sale : R.drawable.module_user_ic_sold_out);

    }
}
