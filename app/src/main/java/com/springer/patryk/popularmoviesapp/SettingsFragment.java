package com.springer.patryk.popularmoviesapp;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_sort_key)));
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);
        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), "")
        );
    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
       String stringValue=o.toString();

        if(preference instanceof ListPreference){
            ListPreference listPreference=(ListPreference)preference;
            int prefIndex=listPreference.findIndexOfValue(stringValue);
            if(prefIndex>=0)
                preference.setSummary(listPreference.getEntries()[prefIndex]);
        }
        getFragmentManager().popBackStack();
        return true;
    }
}
