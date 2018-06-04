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

        BaseBottomDialog show = BottomDialog.create(fragmentManager)
                .setViewListener(new BottomDialog.ViewListener() {
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
                                listener.onCancel();
                            }
                        });

                    }
                })
                .setLayoutRes(R.layout.lib_common_upload_file_dialog_layout)  // dialog layout
                .show();

        return show;

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
