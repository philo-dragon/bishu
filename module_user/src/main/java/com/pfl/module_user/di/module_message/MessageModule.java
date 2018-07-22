package com.pfl.module_user.di.module_message;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.adapter.MessageAdapter;
import com.pfl.module_user.view.HomeView;
import com.pfl.module_user.view.MessageView;
import com.pfl.module_user.viewmodel.HomeViewModel;
import com.pfl.module_user.viewmodel.MessageViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class MessageModule {

    private LifecycleProvider lifecycle;
    private MessageView view;

    public MessageModule(LifecycleProvider lifecycle, MessageView homeView) {
        this.lifecycle = lifecycle;
        this.view = homeView;
    }

    @Provides
    MessageView provideMessageView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    MessageViewModel provideMessageViewModel(LifecycleProvider lifecycle, RetrofitService service, MessageView view) {

        return new MessageViewModel(lifecycle, service, view);
    }

    @Provides
    MessageAdapter provideMessageAdapter() {
        return new MessageAdapter();
    }

}
