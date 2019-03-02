package com.umeng.soexample.zk3demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.umeng.soexample.zk3demo.R;
import com.umeng.soexample.zk3demo.iview.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class DingFragment extends Fragment implements IView {

    @BindView(R.id.fragment_order_fourbtn1)
    RadioButton fragmentOrderFourbtn1;
    @BindView(R.id.fragment_order_fourbtn2)
    RadioButton fragmentOrderFourbtn2;
    @BindView(R.id.fragment_order_fourbtn3)
    RadioButton fragmentOrderFourbtn3;
    @BindView(R.id.fragment_order_fourbtn4)
    RadioButton fragmentOrderFourbtn4;
    @BindView(R.id.fragment_order_fourbtn5)
    RadioButton fragmentOrderFourbtn5;
    @BindView(R.id.fragment_order_fourrg)
    RadioGroup fragmentOrderFourrg;
    @BindView(R.id.recy_dingdan)
    RecyclerView recyDingdan;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ding, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void success(Object success) {

    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fragment_order_fourbtn1, R.id.fragment_order_fourbtn2, R.id.fragment_order_fourbtn3, R.id.fragment_order_fourbtn4, R.id.fragment_order_fourbtn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_order_fourbtn1:
                break;
            case R.id.fragment_order_fourbtn2:
                break;
            case R.id.fragment_order_fourbtn3:
                break;
            case R.id.fragment_order_fourbtn4:
                break;
            case R.id.fragment_order_fourbtn5:
                break;
        }
    }
}
