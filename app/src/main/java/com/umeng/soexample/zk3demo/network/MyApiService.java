package com.umeng.soexample.zk3demo.network;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by W on 2019/3/2 9:30.
 */

public interface MyApiService {
    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String,String> map, @HeaderMap Map<String,String> head);

    @PUT
    Observable<ResponseBody> put(@Url String url, @QueryMap Map<String,Object> map, @HeaderMap Map<String,String> head);

    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String,String> map, @HeaderMap Map<String,String> head);

}
