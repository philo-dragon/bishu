package com.pfl.common.di;

import com.google.gson.Gson;
import com.pfl.common.http.RetrofitService;
import com.pfl.common.utils.BaseUrlManager;
import com.pfl.common.utils.LoggerInterceptor;
import com.pfl.common.utils.PostParamsInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final long TIMEOUT = 5;
    private static final int MAX_TRY_COUNT = 3;

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new PostParamsInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                //.addNetworkInterceptor(new RetryIntercepter(MAX_TRY_COUNT))
                .addNetworkInterceptor(new LoggerInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrlManager.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }


    @Provides
    @Singleton
    RetrofitService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }

    /**
     * 重试拦截器
     */
    public class RetryIntercepter implements Interceptor {

        public int maxRetry;//最大重试次数
        private int retryNum = 0;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）

        public RetryIntercepter(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            System.out.println("retryNum=" + retryNum);
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                System.out.println("retryNum=" + retryNum);
                response = chain.proceed(request);
            }
            return response;
        }
    }

}