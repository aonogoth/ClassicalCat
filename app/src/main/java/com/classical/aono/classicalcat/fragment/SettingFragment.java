package com.classical.aono.classicalcat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.classical.aono.classicalcat.R;
import com.google.android.agera.Updatable;

/**
 * Created by gotha on 2017/10/11.
 */

public class SettingFragment extends Fragment implements Updatable {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);

        return view;
    }

    @Override
    public void update() {

    }
}
