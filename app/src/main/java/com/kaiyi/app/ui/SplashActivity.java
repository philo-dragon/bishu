package com.kaiyi.app.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kaiyi.app.R;
import com.pfl.common.entity.module_user.User;
import com.pfl.common.service.ModuleUserRouteService;
import com.pfl.common.utils.App;
import com.pfl.common.utils.PermissionUtil;
import com.pfl.common.utils.RouteUtils;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.PermissionItem;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "电话权限", R.drawable.permission_ic_phone));
        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "位置权限", R.drawable.permission_ic_location));

        PermissionUtil.requestPermission(permissionItems, new PermissionUtil.SimplePermissionCallback() {

            @Override
            public void onFinish() {
                com.kaiyi.app.constant.LocationManager.getInstance().setLocation(getLocation());
                RouteUtils.actionStart(RouteUtils.APP_MAIN_ACTIVITY);
                User user = ModuleUserRouteService.getUser();
                if (user == null) {
                    RouteUtils.actionStart(RouteUtils.MODULE_USER_ACTIVITY_LOGIN);
                }
            }

            @Override
            public void onClose() {
                RouteUtils.actionStart(RouteUtils.APP_MAIN_ACTIVITY);
            }
        });

    }

    private Location getLocation() {
        LocationManager locationManager = (LocationManager) App.getInstance().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission")
            Location location = locationManager.getLastKnownLocation(provider);
            if (location == null) {
                continue;
            }
            if (bestLocation == null || location.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = location;
            }
        }
        return bestLocation;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getWindow().
                getDecorView().
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
