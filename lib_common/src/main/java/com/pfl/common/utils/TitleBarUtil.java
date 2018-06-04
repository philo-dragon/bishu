package com.pfl.common.utils;

import com.pfl.common.R;
import com.pfl.common.weidget.TitleBar;

/**
 * Created by Administrator on 2018\6\2 0002.
 */

public class TitleBarUtil {

    public static void setToolBarHasBack(TitleBar titleBar, String title) {
        titleBar.setTitle(title);
        titleBar.setTitleColor(App.getInstance().getResources().getColor(R.color.lib_resource_title_color));
        //titleBar.setLeftText("返回");
        titleBar.setLeftImageResource(R.drawable.lib_resource_ic_back);
        titleBar.setLeftTextColor(App.getInstance().getResources().getColor(R.color.lib_resource_title_color));
        titleBar.setDividerHeight(0);
        titleBar.setDividerColor(App.getInstance().getResources().getColor(R.color.title_divider_color));
    }

    public static void setToolBarNoBack(TitleBar titleBar, String title) {
        titleBar.setTitle(title);
        titleBar.setDividerColor(App.getInstance().getResources().getColor(R.color.title_divider_color));
        titleBar.setTitleColor(App.getInstance().getResources().getColor(R.color.lib_resource_title_color));
        titleBar.setDividerHeight(0);
    }

}
