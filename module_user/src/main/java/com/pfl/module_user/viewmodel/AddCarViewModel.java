package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.base.BaseEntyty;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.CarLicence;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.R;
import com.pfl.module_user.view.AddCarView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018\7\23 0023.
 */

public class AddCarViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private AddCarView view;

    public AddCarViewModel(LifecycleProvider lifecycle, RetrofitService service, AddCarView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void getCarLicenceList() {
        service.getCarLicenceList("get")
                .compose(RxSchedulers.<HttpResponse<CarLicence>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<CarLicence>>() {
                    @Override
                    public void onError(Throwable e) {
                        List<BaseEntyty> baseEntyties = new ArrayList<>();
                        setData(baseEntyties);
                    }

                    @Override
                    public void onSuccess(HttpResponse<CarLicence> response) {
                        List<CarLicence.CarLicenceBean> list = response.getData().getList();
                        Observable.fromIterable(list)
                                .compose(RxSchedulers.<CarLicence.CarLicenceBean>compose())
                                .map(new Function<CarLicence.CarLicenceBean, BaseEntyty>() {
                                    @Override
                                    public BaseEntyty apply(CarLicence.CarLicenceBean carLicenceBean) throws Exception {
                                        carLicenceBean.setItemType(R.layout.module_user_item_car_info_layout);
                                        return carLicenceBean;
                                    }
                                })
                                .toList()
                                .subscribe(new Consumer<List<BaseEntyty>>() {
                                    @Override
                                    public void accept(List<BaseEntyty> baseEntyties) throws Exception {
                                        setData(baseEntyties);
                                    }
                                });
                    }
                    @Override
                    public void onFail(HttpResponse<CarLicence> response) {
                        List<BaseEntyty> baseEntyties = new ArrayList<>();
                        setData(baseEntyties);
                    }
                });
    }

    private void setData(List<BaseEntyty> baseEntyties) {
        CarLicence.CarLicenceBean licenceBean = new CarLicence.CarLicenceBean();
        licenceBean.setItemType(R.layout.module_user_item_add_car_layout);
        baseEntyties.add(licenceBean);
        view.onSuccess(baseEntyties);
        view.onRefreshCompelete();
    }


}
