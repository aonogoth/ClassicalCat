package com.classical.aono.classicalcat.fragment;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.util.Log;

import com.classical.aono.classicalcat.MaterialApplication;
import com.classical.aono.classicalcat.R;

/**
 * Created by gotha on 2017/10/11.
 */

public class SettingFragment extends PreferenceFragment{

    //MaterialApplication ma = (MaterialApplication)getActivity().getApplication();
    @Override
    public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preferences_settings);

        //    ListPreference p1 = (ListPreference)findPreference("zihaoshezhi");
        //    ListPreference p2 = (ListPreference)findPreference("hangjianjushezhi");
        //    p1.setSummary(p1.getEntry());
        //    p2.setSummary(p2.getEntry());

            final ListPreference listPreference1 = (ListPreference) getPreferenceManager().findPreference("zihaoshezhi");
            listPreference1.setSummary(listPreference1.getEntry());
            listPreference1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                  @Override
                  public boolean onPreferenceChange(Preference preference, Object o) {
                      int index = listPreference1.findIndexOfValue(o.toString());
                      listPreference1.setSummary(index >= 0 ? listPreference1.getEntries()[index] : null);
                      listPreference1.setValue(o.toString());
                      //ma.setTextSize(o.toString());
                      return false;
                  }
              });
            final ListPreference listPreference2 = (ListPreference) getPreferenceManager().findPreference("hangjianjushezhi");
            listPreference2.setSummary(listPreference2.getEntry());
            listPreference2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object o) {
                    int index = listPreference2.findIndexOfValue(o.toString());
                    listPreference2.setSummary(index >= 0 ? listPreference2.getEntries()[index] : null);
                    listPreference2.setValue(o.toString());
                    //ma.setTextLine(o.toString());
                    return false;
                }
            });
        }
}
