package com.pfl.common.utils;

import android.support.v4.app.FragmentManager;
import android.view.View;

import com.pfl.common.R;

import me.shaohui.bottomdialog.BaseBottomDialog;
import me.shaohui.bottomdialog.BottomDialog;

/**
 * Created by Administrator on 2018\6\4 0004.
 */

public class BottomDialogManager {


    public static BaseBottomDialog uploadDialog(FragmentManager fragmentManager, final OnUploadDialogListener listener) {

        final BottomDialog bottomDialog = BottomDialog.create(fragmentManager);

        BottomDialog.ViewListener viewListener = new BottomDialog.ViewListener() {

            @Override
            public void bindView(View v) {
                v.findViewById(R.id.lib_common_tv_camera).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onCamera();
                    }
                });
                v.findViewById(R.id.lib_common_tv_photo_album).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onPhotoAlbum();
                    }
                });
                v.findViewById(R.id.lib_common_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                    }
                });
            }
        };

        setBottomDialog(bottomDialog, viewListener, R.layout.lib_common_upload_file_dialog_layout);

        return bottomDialog;
    }

    public static BaseBottomDialog unBindDialog(FragmentManager fragmentManager, final View.OnClickListener listener) {

        final BottomDialog bottomDialog = BottomDialog.create(fragmentManager);

        BottomDialog.ViewListener viewListener = new BottomDialog.ViewListener() {

            @Override
            public void bindView(View v) {
                v.findViewById(R.id.lib_common_tv_unbind).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                        listener.onClick(v);
                    }
                });

                v.findViewById(R.id.lib_common_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                    }
                });
            }
        };

        setBottomDialog(bottomDialog, viewListener, R.layout.lib_common_unbind_dialog_layout);

        return bottomDialog;

    }

    public static BaseBottomDialog soundSettingDialog(FragmentManager fragmentManager, final View.OnClickListener listener) {

        final BottomDialog bottomDialog = BottomDialog.create(fragmentManager);

        BottomDialog.ViewListener viewListener = new BottomDialog.ViewListener() {

            @Override
            public void bindView(View v) {
                v.findViewById(R.id.lib_common_tv_male_voice).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                        listener.onClick(v);
                    }
                });

                v.findViewById(R.id.lib_common_tv_female_voice).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                        listener.onClick(v);
                    }
                });

                v.findViewById(R.id.lib_common_tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                    }
                });
            }
        };

        setBottomDialog(bottomDialog, viewListener, R.layout.lib_common_sound_setting_dialog_layout);

        return bottomDialog;

    }


    private static void setBottomDialog(BottomDialog bottomDialog, BottomDialog.ViewListener viewListener, int layoutId) {
        bottomDialog.setViewListener(viewListener)
                .setLayoutRes(layoutId)
                .show();
        bottomDialog.setDimAmount(0.6f);
    }


    public static void dismiss(BaseBottomDialog show) {
        if (null != show) {
            show.dismiss();
        }
    }

    public interface OnUploadDialogListener {
        void onCamera();

        void onPhotoAlbum();

        void onCancel();
    }

}
