package com.umeng.soexample.zk3demo.model;

import com.umeng.soexample.zk3demo.callback.MyCallBack;

import java.util.Map;

/**
 * Created by W on 2019/3/2 9:51.
 */

public interface Model {
    void onGetRequest(String url, Map<String,String> map, Map<String,String> head, Class kind, MyCallBack callBack);
    void onPostRequest(String url, Map<String,String> map, Map<String,String> head, Class kind, MyCallBack callBack);
    void onPutRequest(String url, Map<String,Object> map, Map<String,String> head, Class kind, MyCallBack callBack);
}
