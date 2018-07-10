package com.pfl.module_user.viewmodel;

import com.pfl.common.entity.module_user.FindBean;
import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.FindView;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.ArrayList;

/**
 * Created by rocky on 2018/4/9.
 */

public class FindViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private FindView view;

    public FindViewModel(LifecycleProvider lifecycle, RetrofitService service, FindView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }

    public void requestData() {

        FindBean findBean = new FindBean();

        FindBean.Weather weather = new FindBean.Weather();
        weather.setDesc("晴天");
        weather.setTemperature("40");
        findBean.setWeather(weather);

        FindBean.Location location = new FindBean.Location();
        location.setCity("北京");
        location.setDetail("西直门");
        location.setDistrict("海淀区");
        findBean.setLocation(location);

        FindBean.Traffic_restrict restrict = new FindBean.Traffic_restrict();
        ArrayList<String> list = new ArrayList<>(2);
        list.add("3");
        list.add("7");
        restrict.setRestrict_number(list);
        findBean.setTraffic_restrict(restrict);

        FindBean.Car car = new FindBean.Car();
        car.setPlate_number("京A213765");
        FindBean.Violation violation = new FindBean.Violation();
        violation.setMoney_cost("100");
        violation.setScore_cost("12");
        violation.setUnsolved_num("6");
        car.setViolation(violation);
        findBean.setCar(car);

        view.onSuccess(findBean);


      /*  RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .find()
                .compose(RxSchedulers.<FindBean>compose())
                .compose(lifecycle.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseObserver<FindBean>() {
                    @Override
                    public void onNext(FindBean findBean) {
                        view.onSuccess(findBean);
                    }
                });*/
    }
}
