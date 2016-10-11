package util;

/*
 * Created by 月满轩尼诗 on 2016/10/11.
 */
public interface HttpCallbackListener{
    void onFinish(String response);
    void onError(Exception e);
}