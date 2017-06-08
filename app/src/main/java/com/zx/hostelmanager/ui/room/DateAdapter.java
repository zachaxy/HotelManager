package com.zx.hostelmanager.ui.room;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zx.hostelmanager.R;
import com.zx.hostelmanager.bean.RoomDate;
import com.zx.hostelmanager.rx.RxBus;
import com.zx.hostelmanager.rx.RxBusBaseMessage;
import com.zx.hostelmanager.rx.RxCodeConstants;

import java.util.ArrayList;

/**
 * Created by zhangxin on 2017/4/24 0024.
 * <p>
 * Description :
 */

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {

    ArrayList<RoomDate> list;
    Context mContext;
    //    int curPosition;
    int prePosition = 0;

    int focusColor = 0;
    int normalColor = 0;
    int noColor = 0;

    public DateAdapter(Context mContext, ArrayList<RoomDate> list) {
        this.mContext = mContext;
        this.list = list;
        this.list.get(0).flag = true;

        focusColor = mContext.getResources().getColor(R.color.colorBlue);
        normalColor = mContext.getResources().getColor(R.color.colorTabText);

    }


    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_date, parent, false);
        return new DateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DateViewHolder holder, final int position) {
        holder.week.setText(list.get(position).weekDay);
        holder.date.setText(list.get(position).date);
        if (list.get(position).flag) {
            setItemFocus(holder);
        } else {
            setItemNormal(holder);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把之前的position的flag设置为flase;
                if (prePosition != position) {
                    list.get(prePosition).flag = false;
                    list.get(position).flag = true;
                    notifyItemChanged(prePosition);
                    prePosition = position;
                    setItemFocus(holder);
                    RxBus.getDefault().post(RxCodeConstants.JUMP_TO_ROOM_DATE_SELECT,
                            new RxBusBaseMessage(position, false));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void setItemFocus(DateViewHolder holder) {
        holder.week.setTextColor(focusColor);
        holder.date.setTextColor(focusColor);
        holder.devider.setBackgroundColor(focusColor);
    }

    void setItemNormal(DateViewHolder holder) {
        if (holder == null) {
            return;
        }
        holder.week.setTextColor(normalColor);
        holder.date.setTextColor(normalColor);
        holder.devider.setBackgroundColor(noColor);
    }

    static class DateViewHolder extends RecyclerView.ViewHolder {
        TextView week;
        TextView date;
        ImageView devider;

        public DateViewHolder(View itemView) {
            super(itemView);
            week = (TextView) itemView.findViewById(R.id.tv_item_room_week);
            date = (TextView) itemView.findViewById(R.id.tv_item_room_date);
            devider = (ImageView) itemView.findViewById(R.id.img_item_divider);
        }
    }
}


