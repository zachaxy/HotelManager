package com.zx.hostelmanager.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zx.hostelmanager.R;
import com.zx.hostelmanager.ui.order.OrderActivity;
import com.zx.hostelmanager.ui.bill.BillActivity;
import com.zx.hostelmanager.ui.room.RoomActivity;
import com.zx.hostelmanager.widget.BadgeRadioButton;

import static com.zx.hostelmanager.R.id.img_order;
import static com.zx.hostelmanager.R.id.img_room;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {


    private BadgeRadioButton img_order;
    private BadgeRadioButton img_room;
    private BadgeRadioButton img_bill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();


    }

    private void initView() {
        img_order = (BadgeRadioButton) findViewById(R.id.img_order);
        img_order.setOnClickListener(this);

        img_room = (BadgeRadioButton) findViewById(R.id.img_room);
        img_room.setOnClickListener(this);

        img_bill = (BadgeRadioButton) findViewById(R.id.img_bill);
        img_bill.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_order:
                startActivity(new Intent(this, OrderActivity.class));
                break;
            case R.id.img_room:
                startActivity(new Intent(this, RoomActivity.class));
                break;
            case R.id.img_bill:
                startActivity(new Intent(this, BillActivity.class));
                break;
        }
    }
}