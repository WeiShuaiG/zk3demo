package com.umeng.soexample.zk3demo.model;

import com.google.gson.Gson;
import com.umeng.soexample.zk3demo.callback.MyCallBack;
import com.umeng.soexample.zk3demo.network.RetrofitUtils;

import java.util.Map;

/**
 * Created by W on 2019/3/2 9:53.
 */

public class ModelImpl implements Model {
    @Override
    public void onGetRequest(String url, Map<String, String> map, Map<String, String> head, final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().get(url, map, head).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr,kind);
                callBack.setSuccess(o);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void onPostRequest(String url, Map<String, String> map, Map<String, String> head, final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().post(url, map, head).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr,kind);
                callBack.setSuccess(o);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void onPutRequest(String url, Map<String, Object> map, Map<String, String> head, final Class kind, final MyCallBack callBack) {
        RetrofitUtils.getInstance().put(url, map, head).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr,kind);
                callBack.setSuccess(o);
            }

            @Override
            public void onError(String error) {

            }
        });

    }
}
