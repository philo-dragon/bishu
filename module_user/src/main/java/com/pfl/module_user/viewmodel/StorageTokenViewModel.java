package com.pfl.module_user.viewmodel;

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
import com.blankj.utilcode.util.ToastUtils;
import com.pfl.common.entity.module_user.StorageToken;
import com.pfl.common.http.RetrofitFactory;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.http.RxSchedulers;
import com.pfl.common.utils.App;
import com.pfl.common.utils.BaseObserver;
import com.pfl.module_user.view.StorageTokenView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.io.File;
import java.util.HashMap;

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


    public void getStorageToken(String resource, String req) {
        RetrofitFactory.getInstance()
                .getProxy(RetrofitService.class, service, service)
                .getStorageToken(resource, req)
                .compose(RxSchedulers.<StorageToken>compose())
                .compose(lifecycle.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseObserver<StorageToken>() {
                    @Override
                    public void onNext(StorageToken licence) {
                        view.onStorageToken(licence);
                    }
                });
    }

    public void asyncPutObjectFromLocalFile(StorageToken storageToken, String objectKey, String uploadFilePath) {

        if (null == objectKey || objectKey.equals("")) {
            ToastUtils.showShort("objectKey is not null or empty");
            return;
        }

        File file = new File(uploadFilePath);
        if (!file.exists()) {
            ToastUtils.showShort("图片不存在");
            return;
        }

        // 在移动端建议使用STS的方式初始化OSSClient，更多信息参考：[访问控制]
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(storageToken.getAccessKeyId(), storageToken.getAccessKeySecret(), storageToken.getSecurity());
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(App.getInstance(), storageToken.getEndPoint(), credentialProvider, conf);

        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(storageToken.getBucketName(), objectKey, uploadFilePath);

        // 传入对应的上传回调参数
        HashMap<String, String> callbackParam = new HashMap<>();
        callbackParam.put("callbackUrl", storageToken.getCallbackUrl());
        //callbackBody可以自定义传入的信息
        //"callbackBody":"bucket=${bucket}&object=${object}&etag=${etag}&size=${size}&mimeType=${mimeType}&imageInfo.height=${imageInfo.height}&imageInfo.width=${imageInfo.width}&imageInfo.format=${imageInfo.format}&my_var=${x:my_var}"
        callbackParam.put("callbackBody", "filename=${" + objectKey + "}");
        callbackParam.put("callbackBodyType", "application/json");

        put.setCallbackParam(callbackParam);

        // 传入自定义参数 用户自定义参数的Key一定要以x:开头
        HashMap<String, String> callbackVars = new HashMap<>();
        callbackVars.put("x:uid", "uid");//TODO 需要动态改变为真实uid
        callbackVars.put("x:imageName", objectKey + "_" + System.currentTimeMillis() + ".jpg");
        put.setCallbackVars(callbackVars);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                int progress = (int) (100 * currentSize / totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
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


}
