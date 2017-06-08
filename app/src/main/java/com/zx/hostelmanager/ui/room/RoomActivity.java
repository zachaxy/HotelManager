package com.zx.hostelmanager.ui.room;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zx.hostelmanager.R;
import com.zx.hostelmanager.bean.RoomDate;
import com.zx.hostelmanager.rx.RxBus;
import com.zx.hostelmanager.rx.RxBusBaseMessage;
import com.zx.hostelmanager.rx.RxCodeConstants;

import java.util.ArrayList;
import java.util.Calendar;

import rx.functions.Action1;

public class RoomActivity extends AppCompatActivity {

    ImageView mBack;
    TextView mHotelName;
    RecyclerView rcDateSelect;
    TextView tvDataSelect;

    ArrayList<RoomDate> list;
    DateAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        initData();
        initView();
        initRxBus();
    }

    private void initRxBus() {
        RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TO_ROOM_DATE_SELECT, RxBusBaseMessage.class)
                .subscribe(new Action1<RxBusBaseMessage>() {
                    @Override
                    public void call(RxBusBaseMessage msg) {
                        boolean isMove = (boolean) msg.getObject();
                        //何时需要move,在跳转到日历页面选择了时间之后,需要move,如果只是在横条上选择时间,那么不move;
                        if (isMove) {
                            rcDateSelect.scrollToPosition(msg.getCode());
                        }
                        //TODO:将RecyclerView中的数据切换成对应的时间

                    }
                });
    }


    private void initView() {
        mBack = (ImageView) findViewById(R.id.img_room_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mHotelName = (TextView) findViewById(R.id.tv_hotel_name);

        rcDateSelect = (RecyclerView) findViewById(R.id.rv_date_select);
        rcDateSelect.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new DateAdapter(this, list);
        rcDateSelect.setAdapter(mAdapter);

        tvDataSelect = (TextView) findViewById(R.id.tv_data_select);
        tvDataSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoomActivity.this, RoomDataSelectActivity.class));
            }
        });
    }

    //初始化房间日期
    private void initData() {

        Calendar calendar = Calendar.getInstance();
        //获取当天日期:年/月/日
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;// Java月份从0开始算
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //初始化当月的时间;
        calendar.set(Calendar.YEAR, year);//先指定年份
        calendar.set(Calendar.MONTH, month - 1);//再指定月份 Java月份从0开始算
        int days1 = calendar.getActualMaximum(Calendar.DATE);//获取指定年份中指定月份有几天


        //获取指定年份月份中指定某天是星期几
        calendar.set(Calendar.DAY_OF_MONTH, day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);


        int days2 = 0;
        int days3 = 0;

        if (month == 11) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            days2 = calendar.getActualMaximum(Calendar.DATE);

            calendar.set(Calendar.YEAR, year + 1);
            calendar.set(Calendar.MONTH, 0);
            days3 = calendar.getActualMaximum(Calendar.DATE);
        } else if (month == 12) {
            calendar.set(Calendar.YEAR, year + 1);
            calendar.set(Calendar.MONTH, 0);
            days2 = calendar.getActualMaximum(Calendar.DATE);

            calendar.set(Calendar.YEAR, year + 1);
            calendar.set(Calendar.MONTH, 1);
            days3 = calendar.getActualMaximum(Calendar.DATE);
        } else {
            //month<=10,那么可以确保是在同一年;
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            days2 = calendar.getActualMaximum(Calendar.DATE);

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month + 1);
            days3 = calendar.getActualMaximum(Calendar.DATE);
        }


        int count = days1 - day + 1 + days2 + days3;
        list = new ArrayList<RoomDate>(count);

        for (int i = day; i <= days1; i++) {
            //这里为什么dayofweek+7-2,是因为获得的dayOfWeek的1代表的是周日;
            list.add(new RoomDate((dayOfWeek + 5) % 7 + 1, month + "/" + i));
            dayOfWeek++;
        }

        month++;
        if (month > 12) {
            month = 1;
        }
        for (int i = 1; i <= days2; i++) {
            list.add(new RoomDate((dayOfWeek + 5) % 7 + 1, month + "/" + i));
            dayOfWeek++;
        }

        month++;
        if (month > 12) {
            month = 1;
        }
        for (int i = 1; i <= days3; i++) {
            list.add(new RoomDate((dayOfWeek + 5) % 7 + 1, month + "/" + i));
            dayOfWeek++;
        }
    }
}
/*
1 -> 7
2 -> 1
3 -> 2
4 -> 3

month = 10
11
12

month = 11
12
1


month = 12
1
2
*/