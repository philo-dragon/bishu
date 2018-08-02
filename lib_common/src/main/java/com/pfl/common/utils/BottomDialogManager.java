package com.pfl.common.utils;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.pfl.common.R;

import me.shaohui.bottomdialog.BaseBottomDialog;
import me.shaohui.bottomdialog.BottomDialog;

/**
 * Created by Administrator on 2018\6\4 0004.
 */

public class BottomDialogManager {


    /**
     * 上传文件dialog
     *
     * @param fragmentManager
     * @param listener
     * @return
     */
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

    /**
     * 解除绑定dialog
     *
     * @param fragmentManager
     * @param listener
     * @return
     */
    public static BaseBottomDialog unBindDialog(FragmentManager fragmentManager, final int position, final String name, final OnDeleteIntelligentHardwareListener listener) {

        final BottomDialog bottomDialog = BottomDialog.create(fragmentManager);

        BottomDialog.ViewListener viewListener = new BottomDialog.ViewListener() {

            @Override
            public void bindView(View v) {

                TextView view = v.findViewById(R.id.module_user_dialog_title);
                view.setText("是否解绑" + name + "？");

                v.findViewById(R.id.lib_common_tv_unbind).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                        listener.onDeleteIntelligentHardware(position);
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

    /**
     * 设置声音dialog （男/女 声）
     *
     * @param fragmentManager
     * @param listener
     * @return
     */
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

    public static BaseBottomDialog showGenderDialog(FragmentManager fragmentManager, final View.OnClickListener listener) {

        final BottomDialog bottomDialog = BottomDialog.create(fragmentManager);

        BottomDialog.ViewListener viewListener = new BottomDialog.ViewListener() {

            @Override
            public void bindView(View v) {
                v.findViewById(R.id.lib_common_tv_male).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                        listener.onClick(v);
                    }
                });

                v.findViewById(R.id.lib_common_tv_female).setOnClickListener(new View.OnClickListener() {
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

        setBottomDialog(bottomDialog, viewListener, R.layout.lib_common_gender_setting_dialog_layout);

        return bottomDialog;

    }

    public static BaseBottomDialog showShareDialog(FragmentManager fragmentManager, final View.OnClickListener listener) {

        final BottomDialog bottomDialog = BottomDialog.create(fragmentManager);

        BottomDialog.ViewListener viewListener = new BottomDialog.ViewListener() {

            @Override
            public void bindView(View v) {
                v.findViewById(R.id.lib_common_tv_wechat).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                        listener.onClick(v);
                    }
                });

                v.findViewById(R.id.lib_common_tv_moment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                        listener.onClick(v);
                    }
                });

                v.findViewById(R.id.lib_common_tv_qq).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                        listener.onClick(v);
                    }
                });
                v.findViewById(R.id.lib_common_tv_qzone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                        listener.onClick(v);
                    }
                });
                v.findViewById(R.id.lib_common_tv_sina).setOnClickListener(new View.OnClickListener() {
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

        setBottomDialog(bottomDialog, viewListener, R.layout.lib_common_share_dialog_layout);

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

    public interface OnDeleteIntelligentHardwareListener {
        void onDeleteIntelligentHardware(int position);
    }

}
