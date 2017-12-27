package example.weisente.top.retrofitdemo.simple;

/**
 * Created by san on 2017/12/27.
 */

public class BaseResult {
    String bol;
    String msg;

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return bol;
    }

    public boolean isOk(){
        return "true".equals(bol);
    }
}
