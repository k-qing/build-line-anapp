package com.k.qing.view4jenkins;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class SettingActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);
		
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
	
}
