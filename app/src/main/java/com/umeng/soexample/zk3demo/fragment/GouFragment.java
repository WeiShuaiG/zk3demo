package com.umeng.soexample.zk3demo.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.umeng.soexample.zk3demo.Contacts;
import com.umeng.soexample.zk3demo.R;
import com.umeng.soexample.zk3demo.TijiaoActivity;
import com.umeng.soexample.zk3demo.XiangqingActivity;
import com.umeng.soexample.zk3demo.adapter.GwcAdapter;
import com.umeng.soexample.zk3demo.entity.Querygwc;
import com.umeng.soexample.zk3demo.entity.Submit;
import com.umeng.soexample.zk3demo.iview.IView;
import com.umeng.soexample.zk3demo.presenter.PresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.http.QueryMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class GouFragment extends Fragment implements IView, View.OnClickListener {


    @BindView(R.id.query_gwc)
    RecyclerView queryGwc;
    @BindView(R.id.check)
    CheckBox check;
    @BindView(R.id.gou_heji)
    TextView gouHeji;
    @BindView(R.id.js)
    TextView js;
    Unbinder unbinder;
    private PresenterImpl presenter;
    private GwcAdapter adapter;
    private List<Querygwc.ResultBean> mList = new ArrayList<>();
    private Bundle bundle;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gou, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new PresenterImpl(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        queryGwc.setLayoutManager(layoutManager);
        Map<String,String> head = new HashMap<>();
        head.put("userId",869+"");
        head.put("sessionId","1551445297026869");
        adapter = new GwcAdapter(mList,getActivity());
        queryGwc.setAdapter(adapter);
        Map<String,Object> map = new HashMap<>();
        presenter.onGetRequest(Contacts.GWC_QUERY,null,head,Querygwc.class);
        js.setOnClickListener(this);
        adapter.setOnItemClick(new GwcAdapter.OnItemClick() {
            @Override
            public void onClick(View view, int position) {
                boolean status = adapter.thisCheckStatus(position);
                adapter.setCheckStatus(position, !status);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }

            @Override
            public void onDelete(View view, int position) {
                mList.remove(position);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }

            @Override
            public void onNumber(int position, int number) {
                adapter.setShopCount(position, number);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }

            @Override
            public void onItemClick(View v, int positon, int id) {
                bundle = new Bundle();
                Querygwc querygwc = new Querygwc();
                Querygwc.ResultBean bean = new Querygwc.ResultBean();
                bean.setPrice(mList.get(positon).getPrice());
                bean.setCommodityId(mList.get(positon).getCommodityId());
                bean.setCommodityName(mList.get(positon).getCommodityName());
                bean.setPic(mList.get(positon).getPic());
                bundle.putParcelable("bean", bean);
                Intent intent = new Intent(getActivity(),XiangqingActivity.class);
                intent.putExtra("Bean", bundle);
                startActivity(intent);

            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = adapter.allCheckStatus();
                adapter.setAllCheckStatus(!status);
                check.setChecked(!status);
                adapter.notifyDataSetChanged();
                FlushFooter();
            }
        });
        return view;
    }
    private void FlushFooter() {
        boolean status = adapter.allCheckStatus();
        check.setChecked(status);
        double allPrice = adapter.getAllPrice();
        gouHeji.setText("￥" + allPrice);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(Object success) {
        Querygwc querygwc = (Querygwc) success;
        mList.clear();
        mList.addAll(querygwc.getResult());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.js:
                Intent intent = new Intent(getActivity(), TijiaoActivity.class);
                List<Submit> list = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChildCheck() == true) {
                        Submit bean = new Submit(mList.get(i).getCommodityId(), mList.get(i).getCount(), mList.get(i)
                                .getCommodityName(), mList.get(i).getCount(), mList.get(i).getPic(), mList.get(i).getPrice());
                        list.add(bean);
                    }
                }
                intent.putParcelableArrayListExtra("submit", (ArrayList<? extends Parcelable>) list);
                startActivity(intent);
                break;
        }

    }
}
