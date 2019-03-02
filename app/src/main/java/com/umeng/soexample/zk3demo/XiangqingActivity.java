package com.umeng.soexample.zk3demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.recker.flybanner.FlyBanner;
import com.umeng.soexample.zk3demo.entity.Querygwc;
import com.umeng.soexample.zk3demo.entity.Xiangqing;
import com.umeng.soexample.zk3demo.iview.IView;
import com.umeng.soexample.zk3demo.presenter.PresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XiangqingActivity extends AppCompatActivity implements IView, View.OnClickListener {
    private PresenterImpl presenter;
    private int userId;
    private String sessionId;
    private int commodityId;
    private TextView judgeNum;
    private FlyBanner mGoodsPageBanner;
    private TextView mGoodsPagePrice;
    private TextView mGoodsPageSold;
    private TextView mGoodsPageTitle;
    private TextView mGoodsPageWeight;
    private WebView mGoodsPageImgXiangqing;
    private TextView mGoodsPageJieshao;
    private TextView mJudgeSum;
    private RecyclerView mDetailsRecviewComments;
    private TextView mDetailsTextviewComments;
    private MyScrollView mDetailsScrollChangecolor;
    private ImageView mDetailsImageReturn;
    private TextView mDetailsTextGoodsT;
    private TextView mDetailsTextDetailsT;
    private TextView mDetailsTextCommentsT;
    private TextView mDetailsTextGoods;
    private TextView mDetailsTextDetails;
    private TextView mDetailsTextComments;
    private RelativeLayout mDetailsRelativeChanger;
    private RelativeLayout mDetailsRelatChangecolor;
    private ImageView mBtnCart;
    private RelativeLayout mDetailsRelativeAddshoppingcar;
    private ImageView mBtnBuy;
    private List<String> list = new ArrayList<>();
    private RelativeLayout mDetailsRelativePay;
    private HashMap<String, Object> map = new HashMap<>();
    private List<Querygwc.ResultBean> goodList = new ArrayList<>();
    private Map<String, String> head = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        initView();
        isWhatCommonList();
        Scrollview();
        talkAbout();

    }
    private void talkAbout() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mDetailsRecviewComments.setLayoutManager(manager);
    }
    private void isWhatCommonList() {
        SharedPreferences shopping = getSharedPreferences("ischeck", Context.MODE_PRIVATE);
        userId = shopping.getInt("userId", 0);
        sessionId = shopping.getString("sessionId", null);

        Intent intent = getIntent();
        final Parcelable rxxpBean = intent.getBundleExtra("Bean").getParcelable("bean");

        if (rxxpBean instanceof Querygwc.ResultBean) {

            Map<String, String> map = new HashMap<>();
            commodityId = ((Querygwc.ResultBean) rxxpBean).getCommodityId();
            Log.e("commodityId", commodityId + "");
            map.put("commodityId", String.valueOf(commodityId));

            Map<String, String> headMap = new HashMap<>();
            headMap.put("userId", 869+"");
            headMap.put("sessionId", "1551445297026869");
            presenter.onGetRequest(Contacts.SHOU_XIANGQING, map, headMap, Xiangqing.class);

        }
    }

    //设置滑动页面联动
    private void Scrollview() {
        mDetailsScrollChangecolor.setScrollviewListener(new MyScrollView.ScrollviewListener() {
            @Override
            public void OnScrollChange(MyScrollView scrollView, int l, int t, int oldl, int oldt) {
                if (t <= 0) {
                    //标题显示
                    mDetailsRelativeChanger.setVisibility(View.GONE);
                    //设置标题所在的背景颜色为透明
                    mDetailsRelatChangecolor.setBackgroundColor(Color.argb(0, 0, 0, 0));
                } else if (t > 0 && t < 200) {
                    mDetailsRelativeChanger.setVisibility(View.VISIBLE);
                    //获取ScrollView想下滑动,图片消失部分的比例
                    float scale = (float) t / 200;
                    //根据比例,让标题的颜色由浅入深
                    float alpha = 255 * scale;
                    //设置标题布局的颜色
                    mDetailsRelatChangecolor.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));

                }
                if (0 < t && t < 700) {
                    mDetailsTextGoods.setBackgroundColor(Color.RED);
                    mDetailsTextDetails.setBackgroundColor(Color.WHITE);
                    mDetailsTextComments.setBackgroundColor(Color.WHITE);
                } else if (701 < t && t < 701 + mGoodsPageImgXiangqing.getHeight()) {
                    mDetailsTextGoods.setBackgroundColor(Color.WHITE);
                    mDetailsTextDetails.setBackgroundColor(Color.RED);
                    mDetailsTextComments.setBackgroundColor(Color.WHITE);
                } else {
                    mDetailsTextGoods.setBackgroundColor(Color.WHITE);
                    mDetailsTextDetails.setBackgroundColor(Color.WHITE);
                    mDetailsTextComments.setBackgroundColor(Color.RED);
                }
            }
        });
    }




    private void initView() {
        presenter = new PresenterImpl(this);
        mGoodsPageBanner = (FlyBanner) findViewById(R.id.goods_page_banner);
        mGoodsPageBanner.setOnClickListener(this);
        mGoodsPagePrice = (TextView) findViewById(R.id.goods_page_price);
        mGoodsPagePrice.setOnClickListener(this);
        mGoodsPageSold = (TextView) findViewById(R.id.goods_page_sold);
        mGoodsPageSold.setOnClickListener(this);
        mGoodsPageTitle = (TextView) findViewById(R.id.goods_page_title);
        mGoodsPageTitle.setOnClickListener(this);
        mGoodsPageWeight = (TextView) findViewById(R.id.goods_page_weight);
        mGoodsPageWeight.setOnClickListener(this);
        mGoodsPageImgXiangqing = (WebView) findViewById(R.id.goods_page_img_xiangqing);
        mGoodsPageImgXiangqing.setOnClickListener(this);
        mGoodsPageJieshao = (TextView) findViewById(R.id.goods_page_jieshao);
        mGoodsPageJieshao.setOnClickListener(this);
        mJudgeSum = (TextView) findViewById(R.id.judge_sum);
        mJudgeSum.setOnClickListener(this);
        mDetailsRecviewComments = (RecyclerView) findViewById(R.id.details_recview_comments);
        mDetailsRecviewComments.setOnClickListener(this);
        mDetailsTextviewComments = (TextView) findViewById(R.id.details_textview_comments);
        mDetailsTextviewComments.setOnClickListener(this);
        mDetailsScrollChangecolor = (MyScrollView) findViewById(R.id.details_scroll_changecolor);
        mDetailsScrollChangecolor.setOnClickListener(this);
        mDetailsImageReturn = (ImageView) findViewById(R.id.details_image_return);
        mDetailsImageReturn.setOnClickListener(this);
        mDetailsTextGoodsT = (TextView) findViewById(R.id.details_text_goodsT);
        mDetailsTextGoodsT.setOnClickListener(this);
        mDetailsTextDetailsT = (TextView) findViewById(R.id.details_text_detailsT);
        mDetailsTextDetailsT.setOnClickListener(this);
        mDetailsTextCommentsT = (TextView) findViewById(R.id.details_text_commentsT);
        mDetailsTextCommentsT.setOnClickListener(this);
        mDetailsTextGoods = (TextView) findViewById(R.id.details_text_goods);
        mDetailsTextGoods.setOnClickListener(this);
        mDetailsTextDetails = (TextView) findViewById(R.id.details_text_details);
        mDetailsTextDetails.setOnClickListener(this);
        mDetailsTextComments = (TextView) findViewById(R.id.details_text_comments);
        mDetailsTextComments.setOnClickListener(this);
        mDetailsRelativeChanger = (RelativeLayout) findViewById(R.id.details_relative_changer);
        mDetailsRelativeChanger.setOnClickListener(this);
        mDetailsRelatChangecolor = (RelativeLayout) findViewById(R.id.details_relat_changecolor);
        mDetailsRelatChangecolor.setOnClickListener(this);

    }

    @Override
    public void success(Object success) {
        if (success instanceof Xiangqing){
            Xiangqing.ResultBean bean = ((Xiangqing) success).getResult();
            list.add(bean.getCommodityId() + "");
            String imgStr = bean.getPicture();

            String[] imgStrs = imgStr.split("\\,");
            ArrayList<String> imgList = new ArrayList<>();
            imgList.clear();
            for (int i = 0; i < imgStrs.length; i++) {
                imgList.add(imgStrs[i]);
            }
            mGoodsPageBanner.setImagesUrl(imgList);
            mGoodsPagePrice.setText("￥：" + bean.getPrice() + ".00");
            mGoodsPageSold.setText("已售" + bean.getSaleNum() + "件");
            mGoodsPageTitle.setText(bean.getCommodityName());

            String s = bean.getDetails().trim();
            String dataStr = "<html>"
                    + "<head>"
                    + "<title>欢迎您</title>"
                    + "</head>"
                    + "<body>"
                    + s
                    + "</body>"
                    + "</html>";
            WebSettings webSettings = mGoodsPageImgXiangqing.getSettings();


            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            webSettings.setUseWideViewPort(true);//设置webview推荐使用的窗口
            webSettings.setLoadWithOverviewMode(true);//设置webview加载的页面的模式
            webSettings.setDisplayZoomControls(false);//隐藏webview缩放按钮
            webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
            webSettings.setAllowFileAccess(true); // 允许访问文件
            webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
            webSettings.setSupportZoom(true); // 支持缩放
            mGoodsPageImgXiangqing.setWebViewClient(new WebViewClient());
            mGoodsPageImgXiangqing.loadDataWithBaseURL(null, dataStr, "text/html", "utf-8", null);
            mGoodsPageJieshao.setText(bean.getCategoryName());
            mGoodsPageWeight.setText(bean.getWeight() + "KG");

            Map<String, Object> map = new HashMap<>();
            map.put("commodityId", commodityId + "");
            map.put("page", 1 + "");
            map.put("count", 10 + "");

        }


    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.details_text_goodsT:
                mDetailsScrollChangecolor.fullScroll(ScrollView.FOCUS_UP);
                break;
            case R.id.details_text_detailsT:
                mDetailsScrollChangecolor.scrollTo(0, 705);
                break;
            case R.id.details_text_commentsT:
                mDetailsScrollChangecolor.fullScroll(ScrollView.FOCUS_DOWN);
                break;
        }

    }
}
