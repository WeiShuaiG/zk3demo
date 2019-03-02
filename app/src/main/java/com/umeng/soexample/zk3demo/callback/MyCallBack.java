package com.umeng.soexample.zk3demo.callback;

/**
 * Created by W on 2019/3/2 9:50.
 */

public interface MyCallBack<T> {
    void setSuccess(T success);
    void setError(T error);
}
