package example.weisente.top.retrofitdemo.simple;

/**
 * Created by san on 2017/12/27.
 */

public class UserInfo {

    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "token='" + token + '\'' +
                '}';
    }


}
