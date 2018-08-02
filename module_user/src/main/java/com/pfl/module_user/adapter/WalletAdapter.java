package com.pfl.module_user.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pfl.common.entity.module_user.MineTrip;
import com.pfl.common.entity.module_user.ScoreLog;
import com.pfl.module_user.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2018\7\17 0017.
 */

public class WalletAdapter extends BaseQuickAdapter<ScoreLog.Wallet, BaseViewHolder> {

    private final DateFormat dateFormat;

    public WalletAdapter() {
        super(R.layout.module_user_item_my_wallet);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    }

    @Override
    protected void convert(BaseViewHolder helper, ScoreLog.Wallet item) {
        helper.setText(R.id.module_user_tv_price, item.getValue())
                .setTextColor(R.id.module_user_tv_price, item.getType() == 0 ? mContext.getResources().getColor(R.color.module_user_wallet_add_score) : mContext.getResources().getColor(R.color.module_user_wallet_exchange_score))
                .setText(R.id.module_user_tv_name, item.getTitle())
                .setText(R.id.module_user_tv_time, dateFormat.format(new Date(item.getTime())));
    }
}
