package com.pfl.common.dialog;

import android.os.Bundle;
import android.view.View;

import com.pfl.common.R;

/**
 * Created by Administrator on 2018\6\17 0017.
 */

public class ResultDialogFragment extends BaseDialogFragment {

    public static ResultDialogFragment newInstance() {
        
        Bundle args = new Bundle();
        
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

    }
}
