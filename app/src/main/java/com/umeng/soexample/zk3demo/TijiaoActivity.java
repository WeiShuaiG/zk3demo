package com.umeng.soexample.zk3demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.soexample.zk3demo.adapter.SubmitAdaptr;
import com.umeng.soexample.zk3demo.entity.OrderBean;
import com.umeng.soexample.zk3demo.entity.Submit;
import com.umeng.soexample.zk3demo.iview.IView;
import com.umeng.soexample.zk3demo.presenter.PresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TijiaoActivity extends AppCompatActivity implements View.OnClickListener,IView{

    private RecyclerView mCreatePostRecycle;
    private TextView mCreatePayment;
    private TextView mCreateSubmission;
    private SubmitAdaptr submitAdaptr;
    private ArrayList<Submit> submit;
    private PresenterImpl presenter;
    private int allGoodsPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijiao);
        initView();
    }

    private void initView() {
        presenter = new PresenterImpl(this);
        Intent intent = getIntent();
        submit = intent.getParcelableArrayListExtra("submit");
        mCreatePostRecycle = findViewById(R.id.create_post_recycle);
        mCreatePayment =  findViewById(R.id.create_payment);
        mCreateSubmission = findViewById(R.id.create_Submission);
        mCreateSubmission.setOnClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mCreatePostRecycle.setLayoutManager(manager);
        submitAdaptr = new SubmitAdaptr(submit, this);
        mCreatePostRecycle.setAdapter(submitAdaptr);
        submitAdaptr.notifyDataSetChanged();
        flushBottomLayout();
        mCreatePostRecycle.addItemDecoration(new SpaceItemDecoration(20));
    }
    private void flushBottomLayout() {
        int allGoodsNumber = submitAdaptr.getCount();
        allGoodsPrice = submitAdaptr.getSumPrice();
        mCreatePayment.setText("共" + allGoodsNumber + "件商品需付款" + allGoodsPrice + "元");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create_Submission:

                Map<String, String> body = new HashMap<>();
                Map<String, String> head = new HashMap<>();
                head.put("userId",869+"");
                head.put("sessionId","1551445297026869");
                body.put("orderInfo", submit.toString());
                body.put("totalPrice", allGoodsPrice + "");
                body.put("addressId", 845 + "");
                presenter.onPostRequest(Contacts.DINGDAN, body, head, OrderBean.class);
                break;
        }


    }

    @Override
    public void success(Object success) {
        if (success instanceof OrderBean) {
            Toast.makeText(this, ((OrderBean) success).getMessage(), Toast.LENGTH_SHORT).show();
            if (((OrderBean) success).getStatus().equals("0000")) {
                finish();
            }

        }

    }

    @Override
    public void error(Object error) {

    }
    class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.bottom = mSpace;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = mSpace;
            }

        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }
}
