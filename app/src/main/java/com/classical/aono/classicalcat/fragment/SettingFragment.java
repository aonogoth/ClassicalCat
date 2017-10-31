package com.classical.aono.classicalcat.fragment;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;

import com.classical.aono.classicalcat.R;

/**
 * Created by gotha on 2017/10/11.
 */

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {


@Override
public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    addPreferencesFromResource(R.xml.preferences_settings);

    ListPreference p1 = (ListPreference)findPreference("zihaoshezhi");
    ListPreference p2 = (ListPreference)findPreference("hangjianjushezhi");
    Log.e("aaass",p1.getValue().toString());
    Log.e("bbbss",p2.getKey().toString());
}

    @Override
    public boolean onPreferenceClick(Preference preference) {

        // TODO the rest over to you :)
        Log.e("aaa",((ListPreference) preference).getValue().toString());
        Log.e("bbb",((ListPreference) preference).getKey().toString());
        return false;
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        Log.e("aaa",((ListPreference) preference).getValue().toString());
        Log.e("bbb",((ListPreference) preference).getKey().toString());
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
