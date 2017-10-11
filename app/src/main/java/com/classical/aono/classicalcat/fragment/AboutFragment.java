package com.classical.aono.classicalcat.fragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.classical.aono.classicalcat.R;
import com.classical.aono.classicalcat.adapter.WorkAdapter;
import com.classical.aono.classicalcat.widget.RecyclerItemClickListener;
import com.google.android.agera.Updatable;

/**
 * Created by gotha on 2017/10/11.
 */

public class AboutFragment extends Fragment implements Updatable {

    private TextView mAboutView1;
    private TextView mAboutView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, null);

        mAboutView1 = (TextView) view.findViewById(R.id.tv_card_about_1_1);
        mAboutView2 = (TextView) view.findViewById(R.id.tv_about_version);

        mAboutView2.setText("版本："+getLocalVersionName(getActivity()));

        return view;
    }

    @Override
    public void update() {

    }
    /**
     * 获取本地软件版本号
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
