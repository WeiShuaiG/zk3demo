package com.umeng.soexample.zk3demo.iview;

/**
 * Created by W on 2019/3/2 9:51.
 */

public interface IView<T> {
    void success(T success);
    void error(T error);
}
