package example.weisente.top.retrofitdemo.simple;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by san on 2017/12/27.
 */

public abstract class HttpCallback <T> implements Callback<Result<T>> {
    @Override
    public void onResponse(Call<Result<T>> call, Response<Result<T>> response) {
        Result<T> result = response.body();

        if(result == null){
            // 处理错误情况 ，像 401 等等
            ResponseBody responseBody = response.errorBody();
        }
        if(!result.isOk()){
            onError(result.bol,result.msg);
            return;
        }
    }

    @Override
    public void onFailure(Call<Result<T>> call, Throwable t) {

    }

    public abstract void onSucceed(T result);

    public abstract void onError(String code,String msg);
}
