package com.umeng.soexample.zk3demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.umeng.soexample.zk3demo.fragment.DingFragment;
import com.umeng.soexample.zk3demo.fragment.GouFragment;
import com.umeng.soexample.zk3demo.fragment.MyFragment;
import com.umeng.soexample.zk3demo.fragment.QuanFragment;
import com.umeng.soexample.zk3demo.fragment.ShouFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.shou_vp)
    ViewPager shouVp;
    @BindView(R.id.txt_shou)
    TextView txtShou;
    @BindView(R.id.txt_quanzi)
    TextView txtQuanzi;
    @BindView(R.id.txt_gwc)
    TextView txtGwc;
    @BindView(R.id.txt_ding)
    TextView txtDing;
    @BindView(R.id.txt_my)
    TextView txtMy;
    private List<Fragment> list;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        list.add(new ShouFragment());
        list.add(new QuanFragment());
        list.add(new GouFragment());
        list.add(new DingFragment());
        list.add(new MyFragment());
        shouVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        getData(page);
        shouVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getData(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @OnClick({R.id.txt_shou, R.id.txt_quanzi, R.id.txt_gwc, R.id.txt_ding, R.id.txt_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_shou:
                page = 0;
                break;
            case R.id.txt_quanzi:
                page = 1;
                break;
            case R.id.txt_gwc:
                page = 2;
                break;
            case R.id.txt_ding:
                page = 3;
                break;
            case R.id.txt_my:
                page = 4;
                break;
        }
        getData(page);
    }

    private void getData(int page) {
        shouVp.setCurrentItem(page);
        txtShou.setBackgroundColor(page == 0? Color.GRAY:Color.WHITE);
        txtQuanzi.setBackgroundColor(page == 1? Color.GRAY:Color.WHITE);
        txtGwc.setBackgroundColor(page == 2? Color.GRAY:Color.WHITE);
        txtDing.setBackgroundColor(page == 3? Color.GRAY:Color.WHITE);
        txtMy.setBackgroundColor(page == 4? Color.GRAY:Color.WHITE);
    }
}
