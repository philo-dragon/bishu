package com.pfl.common.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pfl.common.R;
import com.pfl.common.utils.RxClickUtil;

/**
 * Created by Administrator on 2018\6\17 0017.
 */

public class ResultDialogFragment extends BaseDialogFragment {

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAIL = 1;

    public static ResultDialogFragment newInstance(int state, String result, String message) {

        Bundle args = new Bundle();
        args.putInt("state", state);
        args.putString("result", result);
        args.putString("message", message);

        ResultDialogFragment fragment = new ResultDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.lib_common_dialog_success;
    }

    @Override
    public void bindView(View v) {

        int state = getArguments().getInt("state");
        String result = getArguments().getString("result");
        String message = getArguments().getString("message");

        switch (state) {
            case RESULT_SUCCESS:
                ((ImageView) v.findViewById(R.id.lib_common_img_state)).setImageResource(R.drawable.lib_resource_ic_success);
                break;
            case RESULT_FAIL:
                ((ImageView) v.findViewById(R.id.lib_common_img_state)).setImageResource(R.drawable.lib_resource_ic_warning);
                break;
        }

        ((TextView) v.findViewById(R.id.lib_common_tv_result)).setText(result);
        ((TextView) v.findViewById(R.id.lib_common_tv_message)).setText(message);

        RxClickUtil.RxClick(v.findViewById(R.id.lib_common_tv_ok), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
