package com.springer.patryk.popularmoviesapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Patryk on 2016-07-27.
 */
public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content,new SettingsFragment()).commit();
    }
}
