package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.MessageBean;
import com.pfl.common.entity.module_user.MineTrip;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.common.utils.RouteUtils;
import com.pfl.module_user.view.MessageView;
import com.pfl.module_user.view.MyCenterView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018\7\22 0022.
 */

public class MessageViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private MessageView view;

    private int page = 0;
    private int pageSize = 20;

    public MessageViewModel(LifecycleProvider lifecycle, RetrofitService service, MessageView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void refreshData() {
        page = 0;
        getMessageList();
    }

    public void loadmoreData() {
        page++;
        getMessageList();
    }

    private void getMessageList() {

        service
                .getMessageList("get", page, pageSize)
                .compose(RxSchedulers.<HttpResponse<MessageBean>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<MessageBean>>() {

                    @Override
                    public void onSuccess(HttpResponse<MessageBean> response) {
                        super.onSuccess(response);
                        List<MessageBean.Message> data = response.getData().getList();
                        view.onSuccess(page == 0, data);
                        if (page == 0) {
                            view.onRefreshComplete(response.getData().getHas_next() != 0);
                            if (data.isEmpty()) {
                                view.onFail(ExceptionReason.EMPTY_DATA);
                            }
                        } else {
                            view.onLoadmoreComplete(response.getData().getHas_next() != 0);
                        }
                    }

                    @Override
                    public void onFail(HttpResponse<MessageBean> response) {
                        super.onFail(response);
                        Observable.just("")
                                .compose(RxSchedulers.<String>noCheckNetworkCompose())
                                .subscribe(new Consumer<String>() {
                                    @Override
                                    public void accept(String s) throws Exception {
                                        if (page == 0) {
                                            view.onRefreshComplete(false);
                                            view.onFail(ExceptionReason.EMPTY_DATA);
                                        } else {
                                            page--;
                                            view.onLoadmoreComplete(true);
                                        }
                                    }
                                });
                    }

                    @Override
                    public void onException(ExceptionReason reason) {
                        super.onException(reason);
                        if (page == 0) {
                            view.onRefreshComplete(false);
                            view.onFail(ExceptionReason.EMPTY_DATA);
                        } else {
                            view.onLoadmoreComplete(false);
                        }
                    }
                });

    }

    public void updateMessageState(final int position, String messageId) {
        service
                .updateMessageState("put", messageId, 1)
                .compose(RxSchedulers.<HttpResponse<Object>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(HttpResponse response) {
                        super.onSuccess(response);
                        view.updateReadStateSuccess(position);
                    }
                });
    }

}
