package com.pfl.module_user.viewmodel;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.MessageView;
import com.pfl.module_user.view.MyCenterView;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by Administrator on 2018\7\22 0022.
 */

public class MessageViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private MessageView view;


    public MessageViewModel(LifecycleProvider lifecycle, RetrofitService service, MessageView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void getMessageList() {

    }

}
