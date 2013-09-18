package com.k.qing.view4jenkins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	EditText editTextJenkinsURL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);
		editTextJenkinsURL = (EditText)findViewById(R.id.editTextJenkinsURL);
		
		
		Button buttonLogin = (Button)findViewById(R.id.buttonLogin);
		buttonLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String jenkinsURL = editTextJenkinsURL.getText().toString();
				if (jenkinsURL != null) {
					AllViewActivity.JENKINS_URL = jenkinsURL;
					Intent intent = new Intent();
					intent.setClass(LoginActivity.this, AllViewActivity.class);
					startActivity(intent);
				}
				
			}
		});
	}

}
