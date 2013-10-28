package com.k.qing.view4jenkins;

import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class SettingActivity extends PreferenceActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.preferences_header, target);
	}

	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
    		System.out.println("back to home");
    		EditText editTextJenkinsURL = (EditText) findViewById(R.id.editTextJenkinsURL);
    		EditText editTextUserName = (EditText) findViewById(R.id.editTextUser);
    		EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    		
    		String jenkinsURL = editTextJenkinsURL.getText().toString();
    		String userName = editTextUserName.getText().toString();
    		String password = editTextPassword.getText().toString();
    		
    		AllViewActivity.JENKINS_URL = jenkinsURL;
    		SharedPreferences myPrefs = getPreferences(MODE_PRIVATE);
    		Editor editor = myPrefs.edit();
    		editor.putString(AllViewActivity.PREFS_JENKINS_URL, jenkinsURL);
    		editor.commit();
    		
    		Intent intent = new Intent(this, AllViewActivity.class);  
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
            startActivity(intent);  
    		
    		return true;
    	}
		if (item.getItemId() == R.id.menu_icon_settings){
			Intent intent = new Intent(this, SettingActivity.class);
			this.startActivity(intent);
    		return true;
    	}
		return false;
    }
}
