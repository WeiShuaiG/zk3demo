package com.umeng.soexample.zk3demo.presenter;

import com.umeng.soexample.zk3demo.callback.MyCallBack;
import com.umeng.soexample.zk3demo.iview.IView;
import com.umeng.soexample.zk3demo.model.ModelImpl;

import java.util.Map;

/**
 * Created by W on 2019/3/2 9:56.
 */

public class PresenterImpl implements Presenter {
    private IView iView;
    private ModelImpl model;
    public PresenterImpl(IView iView){
        this.iView = iView;
        model = new ModelImpl();

    }
    @Override
    public void onGetRequest(String url, Map<String, String> map, Map<String, String> head, Class kind) {
        model.onGetRequest(url, map, head, kind, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iView.success(success);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);

            }
        });
    }

    @Override
    public void onPostRequest(String url, Map<String, String> map, Map<String, String> head, Class kind) {
        model.onPostRequest(url, map, head, kind, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iView.success(success);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }

    @Override
    public void onPutRequest(String url, Map<String, Object> map, Map<String, String> head, Class kind) {
        model.onPutRequest(url, map, head, kind, new MyCallBack() {
            @Override
            public void setSuccess(Object success) {
                iView.success(success);
            }

            @Override
            public void setError(Object error) {
                iView.error(error);
            }
        });
    }
}
