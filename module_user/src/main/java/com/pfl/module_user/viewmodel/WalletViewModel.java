package com.pfl.module_user.viewmodel;

import com.pfl.common.base.MultiTypeAdapter;
import com.pfl.common.entity.module_user.ScoreLog;
import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.BR;
import com.pfl.module_user.R;
import com.pfl.module_user.view.WalletView;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;

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

        ArrayList<MultiTypeAdapter.IItem> scoreLogs = new ArrayList<>();

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

        view.onSuccess(scoreLogs);

       /* RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .score_log("282307895618", "b9c6c8f954dbbf7274910585a95efce1")
                .compose(RxSchedulers.<List<ScoreLog>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<List<ScoreLog>>() {
                    @Override
                    public void onNext(List<ScoreLog> scoreLogs) {
                        view.onSuccess(scoreLogs);
                    }
                });*/
    }
}
