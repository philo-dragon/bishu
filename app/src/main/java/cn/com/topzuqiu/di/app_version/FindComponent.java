package cn.com.topzuqiu.di.app_version;

import com.pfl.common.di.AppComponent;
import com.pfl.common.di.scope.ActivityScope;
import com.pfl.module_user.fragment.ModuleUserFindFragment;

import cn.com.topzuqiu.ui.MainActivity;
import dagger.Component;

/**
 * Created by rocky on 2018/1/2.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = FindModule.class)
public interface FindComponent {

    void inject(MainActivity activity);
}
