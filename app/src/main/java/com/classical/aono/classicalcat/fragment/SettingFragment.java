package com.classical.aono.classicalcat.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.classical.aono.classicalcat.R;

/**
 * Created by gotha on 2017/10/11.
 */

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {


@Override
public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    addPreferencesFromResource(R.xml.preferences_settings);
}

    @Override
    public boolean onPreferenceClick(Preference preference) {
        // TODO the rest over to you :)
        return false;
    }
}
