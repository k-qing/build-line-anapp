package com.k.qing.view4jenkins.preference;

import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.k.qing.view4jenkins.AllViewActivity;
import com.k.qing.view4jenkins.R;

public class SettingsActivity extends PreferenceActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true); 
		bar.setTitle("Main");
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
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
    		Intent intent = new Intent(this, AllViewActivity.class);  
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
            startActivity(intent);  
    		return true;
    	}
		if (item.getItemId() == R.id.menu_icon_settings){
			Intent intent = new Intent(this, SettingsActivity.class);
			this.startActivity(intent);
    		return true;
    	}
		return false;
    }
}
