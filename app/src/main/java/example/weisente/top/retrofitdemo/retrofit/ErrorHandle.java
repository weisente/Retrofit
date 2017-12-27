package example.weisente.top.retrofitdemo.retrofit;

/**
 * Created by san on 2017/12/27.
 */

public class ErrorHandle {
    public static class ServiceError extends Throwable{
        String errorCode;
        public ServiceError(String errorCode, String errorMsg) {
            super(errorMsg);
            this.errorCode = errorCode;
        }
    }
}
