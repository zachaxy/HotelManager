package com.zx.hostelmanager.ui.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zx.hostelmanager.R;

/**
 * Created by zhangxin on 2017/4/24 0024.
 * <p>
 * Description :
 */

public class OrderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);
        return view;
    }
}
