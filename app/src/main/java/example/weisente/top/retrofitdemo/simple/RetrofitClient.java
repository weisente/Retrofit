package example.weisente.top.retrofitdemo.simple;

import android.util.Log;

import java.io.IOException;

import example.weisente.top.retrofitdemo.retrofit.ErrorHandle;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Emitter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by san on 2017/12/27.
 */

public class RetrofitClient {
    private final static ServiceApi mServiceApi;

    static {
        //拦截器
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.e("TAG",message);



                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                //生成一个拦截器是用来修改baseurl
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        //获取request的创建者builder
                        Request.Builder builder = request.newBuilder();
                        //从request中获取headers，通过给定的键url_name
//                        List<String> headerValues = request.headers("url_name");
//                        if (headerValues != null && headerValues.size() > 0){
//                            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
////                            builder.removeHeader(HttpConfig.HEADER_KEY);
////                            String headerValue = headerValues.get(0);
//                            Log.e("TAG",headerValues.toString());
//                        }
                        Log.e("TAG+123", request.url().toString());
//                        request.
//                        request.url()
                        return null;
                    }
                })

                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ppw.zmzxd.cn/index.php/api/v1/")
                //转换工厂
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        mServiceApi = retrofit.create(ServiceApi.class);
    }
    public static ServiceApi getServiceApi() {
        return mServiceApi;
    }

    public static <T> Observable.Transformer<Result<T>, T> transformer(){
//        Observable  到  Observable
        return new Observable.Transformer<Result<T>, T>() {
            @Override
            public Observable<T> call(Observable<Result<T>> resultObservable) {
                return resultObservable.flatMap(new Func1<Result<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(Result<T> tResult) {
                        if(tResult.isOk()){
                            // 返回成功
                            return createObservable(tResult.data);
                        }else {
                            // 返回失败
                            return Observable.error(new ErrorHandle.ServiceError("",tResult.getMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <T> Observable<T> createObservable(final T data) {
        return Observable.create(new Action1<Emitter<T>>() {
            @Override
            public void call(Emitter<T> tEmitter) {
                tEmitter.onNext(data);
                tEmitter.onCompleted();
            }
        }, Emitter.BackpressureMode.NONE);
    }

}
