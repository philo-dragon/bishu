package com.pfl.common.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.pfl.common.R;

/**
 * Created by Administrator on 2018\4\25 0025.
 */

public class DialogManager {

    private static AlertDialog dialog;

    public static ProgressDialog uploadFileProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
        dialog.setCancelable(true);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setTitle("提示");
        dialog.setMessage("正在上传文件");
        dialog.setMax(100);
        return dialog;
    }

    public static void showSingleBtnDialog(Activity activity, String message) {
        dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setNegativeButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog = builder.show();
        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(android.R.color.holo_blue_dark));
    }

    public static void showTwoBtnDialog(Activity activity, String message, final OnDialogClickListener clickListener) {
        dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (clickListener != null) {
                    clickListener.onNegative();
                }
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (clickListener != null) {
                    clickListener.onPositive();
                }
            }
        });

        dialog = builder.show();

        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(android.R.color.holo_blue_dark));
        dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(activity.getResources().getColor(android.R.color.holo_blue_dark));
    }

    public static void dismiss() {
        synchronized (DialogManager.class) {
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }
        }
    }

    public interface OnDialogClickListener {
        void onNegative();//取消

        void onPositive();//确定
    }


    public static class SimpleDialogClickListener implements OnDialogClickListener {

        @Override
        public void onNegative() {

        }

        @Override
        public void onPositive() {

        }
    }
}
