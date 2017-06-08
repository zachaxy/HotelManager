package com.zx.hostelmanager.ui.order;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zx.hostelmanager.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private ImageView mBack;
    private TextView mOrderFilter;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ArrayList<String> mTitleList = new ArrayList<>(3);
    private ArrayList<Fragment> mFragments = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.img_order_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mOrderFilter = (TextView) findViewById(R.id.tv_order_filter);
        mOrderFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this, OrderFilterActivity.class));
            }
        });
        mTabLayout = (TabLayout) findViewById(R.id.tab_order_title);
        mViewPager = (ViewPager) findViewById(R.id.vp_order);

        mTitleList.add("待处理订单");
        mTitleList.add("今日入住");
        mTitleList.add("全部订单");

        mFragments.add(new OrderFragment());
        mFragments.add(new OrderFragment());
        mFragments.add(new OrderFragment());

        OrderPagerAdapter adapter = new OrderPagerAdapter(getSupportFragmentManager(), mFragments, mTitleList);

        mViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mTabLayout.setupWithViewPager(mViewPager);
    }
}
