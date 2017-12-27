package example.weisente.top.retrofitdemo.simple;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by san on 2017/12/27.
 */

public interface ServiceApi {
    @POST("login")// 登录接口 GET(相对路径)
    @FormUrlEncoded
    Observable<Result<UserInfo>> userLogin(
            // @Query(后台需要解析的字段)
            @Field("account") String userName,
            @Field("password") String userPwd);
}
