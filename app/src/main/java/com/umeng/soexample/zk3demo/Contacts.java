package com.umeng.soexample.zk3demo;


/**
 * 用来存放所有接口
 */
public class Contacts {
    public static final String BASE_URL = "http://172.17.8.100/small/";

    public static final String SHOU_XIANGQING = "commodity/v1/findCommodityDetailsById";

    //查询购物车
    public static final String GWC_QUERY = "order/verify/v1/findShoppingCart";

    //同步购物车
    public static final String GWC_ADD = "order/verify/v1/syncShoppingC" +
            "art";

    //地址列表

    public static final String DINGDAN = "order/verify/v1/createOrder";

    public static final String DINGDAN_QUE = "order/verify/v1/findOrderListByStatus";











}
