package com.umeng.soexample.zk3demo.presenter;

import com.umeng.soexample.zk3demo.callback.MyCallBack;

import java.util.Map;

/**
 * Created by W on 2019/3/2 9:55.
 */

public interface Presenter {
    void onGetRequest(String url, Map<String,String> map, Map<String,String> head, Class kind);
    void onPostRequest(String url, Map<String,String> map, Map<String,String> head, Class kind);
    void onPutRequest(String url, Map<String,Object> map, Map<String,String> head, Class kind);
}
