package com.pfl.common.utils;

import net.arvin.socialhelper.SocialHelper;
import net.arvin.socialhelper.entities.QQShareEntity;
import net.arvin.socialhelper.entities.ShareEntity;
import net.arvin.socialhelper.entities.WBShareEntity;
import net.arvin.socialhelper.entities.WXShareEntity;

import java.util.ArrayList;

/**
 * Created by arvinljw on 17/11/27 17:33
 * Function：
 * Desc：
 */
public enum SocialUtil {
    INSTANCE();

    public SocialHelper socialHelper;

    SocialUtil() {
        socialHelper = new SocialHelper.Builder()
                .setQqAppId("qqAppId")
                .setWxAppId("wx2af97f0265b1adcc")
                .setWxAppSecret("wxAppSecret")
                .setWbAppId("wbAppId")
                .setWbRedirectUrl("wbRedirectUrl")
                .build();
    }


    private String imgUrl = "https://upload-images.jianshu.io/upload_images/3157525-afe6f0ba902eb523.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240";
    private String localImgUrl = "/storage/emulated/0/DCIM/Camera/IMG_20180422_113944.jpg";

    private String title = "比数出行";
    private String summary = "去中心化链接出行数据和服务";
    private static String targetUrl = "http://sj.qq.com/myapp/detail.htm?apkName=" + App.getInstance().getPackageName();
    public static String APP_YYB_URL = targetUrl;

    private final int img = 1;
    private final int text = 2;
    private final int web = 3;

    public ShareEntity createQQShareEntity(int checkedRadioButtonId) {
        ShareEntity shareEntity = null;
        switch (checkedRadioButtonId) {
            case img:
                shareEntity = QQShareEntity.createImageInfo(localImgUrl, "ni6");
                break;
            case text:
                shareEntity = QQShareEntity.createImageTextInfo(title, targetUrl, imgUrl, summary, "ni6");
                break;
            case web:
                //分享到qq空间，因为qq图文就包含了targetUrl所以比较常用
                ArrayList<String> imgUrls = new ArrayList<>();
                imgUrls.add(imgUrl);
                shareEntity = QQShareEntity.createImageTextInfoToQZone(title, targetUrl, imgUrls, summary, "ni6");
                break;
        }
        return shareEntity;
    }

    public ShareEntity createWXShareEntity(boolean isMoment, int checkedRadioButtonId, int defaultImgRes) {
        ShareEntity shareEntity = null;
        switch (checkedRadioButtonId) {
            case img:
                shareEntity = WXShareEntity.createImageInfo(isMoment, localImgUrl);
                break;
            case text:
                //微信图文是分开的，但是在分享到朋友圈的web中是可以有混合的
                shareEntity = WXShareEntity.createImageInfo(isMoment, defaultImgRes);
                break;
            case web:
                shareEntity = WXShareEntity.createWebPageInfo(isMoment, targetUrl, defaultImgRes, title, summary);
                break;
        }
        return shareEntity;
    }

    public ShareEntity createWBShareEntity(int checkedRadioButtonId, int defaultImgRes) {
        ShareEntity shareEntity = null;
        switch (checkedRadioButtonId) {
            case img:
                shareEntity = WBShareEntity.createImageTextInfo(localImgUrl, title);
                break;
            case text:
                shareEntity = WBShareEntity.createImageTextInfo(defaultImgRes, title);
                break;
            case web:
                shareEntity = WBShareEntity.createWebInfo(targetUrl, title, summary, defaultImgRes, "这是要说的内容");
                break;
        }
        return shareEntity;
    }

}