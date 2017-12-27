package example.weisente.top.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import example.weisente.top.retrofitdemo.simple.BaseSubscriber;
import example.weisente.top.retrofitdemo.simple.RetrofitClient;
import example.weisente.top.retrofitdemo.simple.UserInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitClient.getServiceApi().userLogin("14726932514", "123456")
                .compose(RetrofitClient.<UserInfo>transformer())
                .subscribe(new BaseSubscriber<UserInfo>() {
                    @Override
                    protected void onError(String errorCode, String errorMessage) {
                        toast(errorMessage);
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
//                        Integer.parseInt(userInfo.userName);// 不会停止运行
                        Log.e("TAG",userInfo.toString());
                    }
                });
    }

    private void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
