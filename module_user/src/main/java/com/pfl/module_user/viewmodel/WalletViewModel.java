package com.pfl.module_user.viewmodel;

import com.pfl.common.base.MultiTypeAdapter;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.ScoreLog;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.BR;
import com.pfl.module_user.R;
import com.pfl.module_user.view.WalletView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by rocky on 2018/4/9.
 */

public class WalletViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private WalletView view;

    public WalletViewModel(LifecycleProvider lifecycle, RetrofitService service, WalletView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData() {

       /* ArrayList<MultiTypeAdapter.IItem> scoreLogs = new ArrayList<>();

        ScoreLog scoreLog = new ScoreLog();
        scoreLog.setTitle("每日登录");
        scoreLog.setTime("2018-05-25 16:12");
        scoreLog.setValue("+5");
        scoreLog.setType(0);
        scoreLog.setLayout(R.layout.module_user_item_my_wallet);
        scoreLog.setVariableId(BR.item);
        scoreLogs.add(scoreLog);


        scoreLog = new ScoreLog();
        scoreLog.setTitle("出售行程");
        scoreLog.setTime("2018-05-25 16:12");
        scoreLog.setValue("+5");
        scoreLog.setType(0);
        scoreLog.setLayout(R.layout.module_user_item_my_wallet);
        scoreLog.setVariableId(BR.item);
        scoreLogs.add(scoreLog);

        scoreLog = new ScoreLog();
        scoreLog.setTitle("商品兑换");
        scoreLog.setTime("2018-05-25 16:12");
        scoreLog.setValue("-100");
        scoreLog.setType(1);
        scoreLog.setLayout(R.layout.module_user_item_my_wallet);
        scoreLog.setVariableId(BR.item);
        scoreLogs.add(scoreLog);

        scoreLog = new ScoreLog();
        scoreLog.setTitle("车主认证");
        scoreLog.setTime("2018-05-25 16:12");
        scoreLog.setValue("+5");
        scoreLog.setType(0);
        scoreLog.setLayout(R.layout.module_user_item_my_wallet);
        scoreLog.setVariableId(BR.item);
        scoreLogs.add(scoreLog);

        scoreLog = new ScoreLog();
        scoreLog.setTitle("出售行程");
        scoreLog.setTime("2018-05-25 16:12");
        scoreLog.setValue("+5");
        scoreLog.setType(0);
        scoreLog.setLayout(R.layout.module_user_item_my_wallet);
        scoreLog.setVariableId(BR.item);
        scoreLogs.add(scoreLog);

        view.onSuccess(scoreLogs);*/

        service
                .score_log("get")
                .compose(RxSchedulers.<HttpResponse<ScoreLog>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<ScoreLog>>() {
                    @Override
                    public void onSuccess(HttpResponse<ScoreLog> scoreLogs) {

                        List<ScoreLog.Wallet> data = scoreLogs.getData().getList();

                        Observable.fromIterable(data).map(new Function<ScoreLog.Wallet, MultiTypeAdapter.IItem>() {
                            @Override
                            public MultiTypeAdapter.IItem apply(ScoreLog.Wallet wallet) throws Exception {
                                wallet.setType(wallet.getValue().startsWith("-") ? 1 : 0);
                                wallet.setType(R.layout.module_user_item_my_wallet);
                                wallet.setVariableId(BR.item);
                                return wallet;
                            }
                        })
                                .toList()
                                .subscribe(new Consumer<List<MultiTypeAdapter.IItem>>() {
                                    @Override
                                    public void accept(List<MultiTypeAdapter.IItem> items) throws Exception {
                                        view.onSuccess(items);
                                    }
                                });

                    }
                });
    }
}
