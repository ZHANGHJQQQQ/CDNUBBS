package com.twinkle.cdnubbs;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;
import com.twinkle.cdnubbs.java.content.InfoAdapter;
import com.twinkle.cdnubbs.java.utils.Init;
import com.twinkle.cdnubbs.java.utils.Util;
import com.twinkle.cdnubbs.user.User;

import org.xutils.image.ImageOptions;
import org.xutils.x;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {

    private ListView lv_info;
    private TextView tvw_info_fans, tvw_info_foucs, tvw_info_admin, tvw_info_level, tvw_info_name;
    private ImageView ivw_info_fans, ivw_info_focus;
    private LinearLayout llt_info_admin;
    private SmartImageView siv_info_header;
    private InfoAdapter infoAdapter;


    protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            User user = Util.getUser();
            tvw_info_name.setText(user.getName());
            x.image().bind(siv_info_header, user.getPic(),new ImageOptions.Builder().setCircular(true).build());
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        // 在当前的activity中注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(Init.UpdateInfo);
        getActivity().registerReceiver(this.broadcastReceiver, filter);
    }

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(this.broadcastReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        lv_info = (ListView) view.findViewById(R.id.lv_info);

        tvw_info_fans = (TextView) view.findViewById(R.id.tvw_info_fans);
        tvw_info_foucs = (TextView) view.findViewById(R.id.tvw_info_foucs);

        ivw_info_fans = (ImageView) view.findViewById(R.id.ivw_info_fans);
        ivw_info_focus = (ImageView) view.findViewById(R.id.ivw_info_focus);

        llt_info_admin = (LinearLayout) view.findViewById(R.id.llt_info_admin);

        tvw_info_admin = (TextView) view.findViewById(R.id.tvw_info_admin);
        tvw_info_level = (TextView) view.findViewById(R.id.tvw_info_level);
        tvw_info_name = (TextView) view.findViewById(R.id.tvw_info_name);
        siv_info_header = (SmartImageView) view.findViewById(R.id.siv_info_header);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        set_lv();
        user_info();


    }

    private void user_info() {
        User user = Util.getUser();
        tvw_info_name.setText(user.getName());
        tvw_info_admin.setText(user.getUsername());
        tvw_info_level.setText(String.valueOf(user.getLevel()));
       // siv_info_header.setImageUrl(user.getPic());
        x.image().bind(siv_info_header, user.getPic(),new ImageOptions.Builder().setCircular(true).build());
        llt_info_admin.setOnClickListener(this);
        ivw_info_fans.setOnClickListener(this);
        ivw_info_focus.setOnClickListener(this);

    }

    private void set_lv() {
        infoAdapter = new InfoAdapter(getActivity());
        lv_info.setAdapter(infoAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llt_info_admin:
                startActivity(new Intent(getActivity(), AdminActivity.class));
                break;
            case R.id.ivw_info_focus:
                break;
            case R.id.ivw_info_fans:
                break;
            default:
                break;
        }
    }
}
