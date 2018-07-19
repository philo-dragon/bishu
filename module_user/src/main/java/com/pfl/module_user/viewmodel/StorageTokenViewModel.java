package com.pfl.module_user.viewmodel;

import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.entity.base.HttpResponse;
import com.pfl.common.entity.module_user.StorageToken;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.service.ModuleAppConfigurationRouteService;
import com.pfl.common.service.ModuleAppLocationRouteService;
import com.pfl.common.service.ModuleUserRouteService;
import com.pfl.common.utils.App;
import com.pfl.common.utils.BaseObserver;
import com.pfl.common.utils.BaseUrlManager;
import com.pfl.common.utils.PostParamsInterceptor;
import com.pfl.module_user.constant.UserInfoManager;
import com.pfl.module_user.view.StorageTokenView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import static com.blankj.utilcode.util.PhoneUtils.getIMEI;

/**
 * ALi OSS 上传图片服务器接口调用
 */

public class StorageTokenViewModel {

    private LifecycleProvider lifecycle;
    private RetrofitService service;
    private StorageTokenView view;

    public StorageTokenViewModel(LifecycleProvider lifecycle, RetrofitService service, StorageTokenView view) {
        this.lifecycle = lifecycle;
        this.service = service;
        this.view = view;
    }


    public void getStorageToken() {
        service
                .getStorageToken("get")
                .compose(RxSchedulers.<HttpResponse<StorageToken>>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<HttpResponse<StorageToken>>() {
                    @Override
                    public void onSuccess(HttpResponse<StorageToken> response) {
                        view.onStorageToken(response.getData());
                    }
                });
    }

    /* {
             'bucket':'',
             'object':'',
             'etag':'',
             'size':123,
             'mimeType':'',
             'imageInfo':{
                 'height':'',
                 'width':'',
                 'format':'',
     },
         'x:action': 'post',
             'x:resource':'xxxx',
             'x:id': 'xxxxxxx',
             'x:seq':0,
             'x:全局参数'
     }*/

    /**
     * @param storageToken
     * @param seq            表示文件的正反面，0表示正面，1表示反面，默认为0
     * @param resource       文件类型，取值['id_card', 'driver_licence', 'car_licence']  |
     * @param uploadFilePath 上传文件path
     */
    public void asyncPutObjectFromLocalFile(StorageToken storageToken, String seq, String resource, String uploadFilePath) {


        File file = new File(uploadFilePath);
        if (!file.exists()) {
            ToastUtils.showShort("图片不存在");
            return;
        }
        String bucketName = ModuleAppConfigurationRouteService.getConfiguration().getOss().getBucketName();
        //获取OSSClient
        OSS oss = createOSS(storageToken);
        //图片存储位置为<bucket>下的<object>, object命名规则 '<uid>/<resource>_<unix时间戳>.png'
        String uploadToOSSPath = UserInfoManager.getInstance().getUser().getUid()
                + File.separator
                + resource
                + "_"
                + System.currentTimeMillis()
                + ".png";
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucketName, uploadToOSSPath, uploadFilePath);
        int[] widthAndHeight = getImageWidthAndHeightByPath(uploadFilePath);
        // 传入对应的上传回调参数
        HashMap<String, String> callbackParam = new HashMap<>();
        callbackParam.put("callbackUrl", ModuleAppConfigurationRouteService.getConfiguration().getOss().getCallbackUrl());
        //callbackBody可以自定义传入的信息
        JSONObject root = new JSONObject();
        JSONObject imageInfo = new JSONObject();
        JSONObject minfoData = new JSONObject();
        JSONObject geoData = new JSONObject();
        try {

            // ----------------------  全局请求参数  -------------------------- //
            String sign = "";
            if (ModuleUserRouteService.getUser() != null) {
                sign = EncryptUtils.encryptMD5ToString(ModuleUserRouteService.getUser().getToken() + "_" + "/storage_callback").toLowerCase();
            }
            String request_id = EncryptUtils.encryptMD5ToString(getIMEI() + "-" + DeviceUtils.getMacAddress() + "-" + System.currentTimeMillis());

            root.put("x:uid", UserInfoManager.getInstance().getUser().getUid() == null ? "" : UserInfoManager.getInstance().getUser().getUid());
            root.put("x:sign", sign);//sign=md5("token_/configuraion")
            root.put("x:request_id", request_id);//每个请求唯一,可用imei-mac-timestamp生成的md5作为request_id
            root.put("x:req_from", "Android");
            root.put("x:req_ver", "1");

            minfoData.put("os", "android");
            minfoData.put("os_ver", android.os.Build.VERSION.RELEASE);
            minfoData.put("app_ver", AppUtils.getAppVersionName());
            minfoData.put("dist", "umeng");
            minfoData.put("imei", PhoneUtils.getIMEI());
            minfoData.put("mac_addr", DeviceUtils.getMacAddress());
            minfoData.put("network", PostParamsInterceptor.getNetWork());
            minfoData.put("screen_width", ScreenUtils.getScreenWidth());
            minfoData.put("screen_height", ScreenUtils.getScreenHeight());
            minfoData.put("device_brand", PostParamsInterceptor.getDeviceBrand());
            minfoData.put("device_model", DeviceUtils.getModel());
            root.put("x:minfo", minfoData);

            Location location = ModuleAppLocationRouteService.getLocation();
            geoData.put("x", location == null ? "" : location.getLatitude());
            geoData.put("y", location == null ? "" : location.getLongitude());
            root.put("x:geo", geoData);


            // ----------------------  OSS请求参数  -------------------------- //
            root.put("object", uploadToOSSPath);
            root.put("bucket", bucketName);
            root.put("size", file.length());
            root.put("mimeType", "image/png");

            root.put("x:action", "post");
            root.put("x:id", ModuleUserRouteService.getUser().getUid());
            root.put("x:seq", Integer.valueOf(seq));
            root.put("x:resource", resource);

            imageInfo.put("width", widthAndHeight[0]);
            imageInfo.put("height", widthAndHeight[1]);
            imageInfo.put("format", "png");
            root.put("imageInfo", imageInfo);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        callbackParam.put("callbackBody", root.toString());
        callbackParam.put("callbackBodyType", "application/json");
        put.setCallbackParam(callbackParam);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                int progress = (int) (100 * currentSize / totalSize);
                view.uploadProgress(progress);
            }
        });

        oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                view.uploadSuccess();
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                view.uploadFail();
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }

    @NonNull
    private OSS createOSS(StorageToken storageToken) {
        // 在移动端建议使用STS的方式初始化OSSClient，更多信息参考：[访问控制]
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(storageToken.getAccessKeyId(), storageToken.getAccessKeySecret(), storageToken.getSecurity());
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        return new OSSClient(App.getInstance(), ModuleAppConfigurationRouteService.getConfiguration().getOss().getEndPoint(), credentialProvider, conf);
    }


    private int[] getImageWidthAndHeightByPath(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null

        int[] bitmapSize = new int[2];
        bitmapSize[0] = options.outWidth;
        bitmapSize[1] = options.outHeight;
        return bitmapSize;
    }

}
