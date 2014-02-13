package com.k.qing.view4jenkins.preference;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.k.qing.view4jenkins.R;

public class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener  {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_jenkins_info);
		Preference jenkinsURLPref = findPreference(Const.PREFS_JENKINS_URL);
        // Set summary to be the user-description for the selected value
		jenkinsURLPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(Const.PREFS_JENKINS_URL, ""));
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (key.equals(Const.PREFS_JENKINS_URL)) {
			Preference jenkinsURLPref = findPreference(key);
            // Set summary to be the user-description for the selected value
			jenkinsURLPref.setSummary(sharedPreferences.getString(key, ""));
		}
		
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    getPreferenceScreen().getSharedPreferences()
	            .registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    getPreferenceScreen().getSharedPreferences()
	            .unregisterOnSharedPreferenceChangeListener(this);
	}
}
