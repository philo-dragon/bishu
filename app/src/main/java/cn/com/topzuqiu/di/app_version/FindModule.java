package cn.com.topzuqiu.di.app_version;

import com.pfl.common.http.RetrofitService;
import com.pfl.module_user.view.FindView;
import com.pfl.module_user.viewmodel.FindViewModel;
import com.trello.rxlifecycle2.LifecycleProvider;

import cn.com.topzuqiu.view.AppVersionView;
import cn.com.topzuqiu.viewmodel.AppVersionViewModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rocky on 2018/1/2.
 */

@Module
public class FindModule {

    private LifecycleProvider lifecycle;
    private AppVersionView view;

    public FindModule(LifecycleProvider lifecycle, AppVersionView findView) {
        this.lifecycle = lifecycle;
        this.view = findView;
    }

    @Provides
    AppVersionView provideHomeView() {
        return view;
    }

    @Provides
    LifecycleProvider provideLifecycleProvider() {
        return lifecycle;
    }

    @Provides
    AppVersionViewModel provideFindViewModel(LifecycleProvider lifecycle, RetrofitService service, AppVersionView view) {

        return new AppVersionViewModel(lifecycle, service, view);
    }
}
