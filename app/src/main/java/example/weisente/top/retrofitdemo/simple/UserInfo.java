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

    //    public String userName;
//    public String userSex;
//
//    @Override
//    public String toString() {
//        return "UserInfo{" +
//                "userName='" + userName + '\'' +
//                ", userSex='" + userSex + '\'' +
//                '}';
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserSex() {
//        return userSex;
//    }
//
//    public void setUserSex(String userSex) {
//        this.userSex = userSex;
//    }
}
