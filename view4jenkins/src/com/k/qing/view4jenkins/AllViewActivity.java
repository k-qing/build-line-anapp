package com.k.qing.view4jenkins;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.k.qing.view4jenkins.bean.JViewer;
import com.k.qing.view4jenkins.bean.JenkinsProject;
import com.k.qing.view4jenkins.bean.JenkinsView;
import com.k.qing.view4jenkins.util.JenkinsJsonParser;

public class AllViewActivity extends Activity {

	private JViewer jViewer;
	
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				List<JenkinsView> jenkinsViewList = (List<JenkinsView>) msg.obj;
				update(jenkinsViewList);
				break;
			}
		};
	};
	
	
	public static ProgressDialog progressDialog;
	
	public static String jenkinsURL = "http://147.128.17.152:8080/jenkins/";
	
	private Handler progressDialogHandler = new AllViewHandler(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (jViewer != null) {

		} else {
			setContentView(R.layout.login_view);
			Button buttonLogin = (Button)findViewById(R.id.buttonLogin);
			
			buttonLogin.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					progressDialog = new ProgressDialog(AllViewActivity.this);
					progressDialog.setMessage("Please wait...");
					progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
					progressDialog.show();
					
					new Thread() {
						@Override
						public void run() {
							EditText editTextJenkinsURL = (EditText) findViewById(R.id.editTextJenkinsURL);
							EditText editTextUserName = (EditText) findViewById(R.id.editTextUser);
							EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
							
							String jenkinsURL = editTextJenkinsURL.getText().toString();
							String userName = editTextUserName.getText().toString();
							String password = editTextPassword.getText().toString();
							
							try {
								jViewer = new JViewer(jenkinsURL, userName, password);
								jViewer.initConnection();
								progressDialogHandler.sendEmptyMessage(0);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								progressDialog.dismiss();
							}
						}
					}.start();
				}
			});
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		progressDialog.dismiss();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_view, menu);
		return true;
	}


	public void update(List<JenkinsView> jenkinsViewList) {
//		childrenData.clear();
//		groupData.clear();
//		try {
//			for(JenkinsView jenkinsView : jenkinsViewList) {
//				groupData.add(jenkinsView.getName());
//				List<JenkinsProject> projectList = jenkinsView.getJenkinsProjectList();
//				List<String> projectNameList = new ArrayList<String>();
//				for(JenkinsProject jenkinsProject : projectList) {
//					projectNameList.add(jenkinsProject.getName());
//				}
//				childrenData.add(projectNameList);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		expandableAdapter.notifyDataSetChanged();
	}
	
//	private class UpdateDataRunnable implements Runnable {
//		@Override
//		public void run() {
//			Message message = new Message();
//			message.what = 1;
//			
//			JenkinsJsonParser jenkinsJsonParser = new JenkinsJsonParser();
//			List<JenkinsView> jenkinsViewList = jenkinsJsonParser.getViewList(jenkinsURL);
//			
//			message.obj = jenkinsViewList;
//			
//			handler.sendMessage(message);
//		}
//	}
	
	

}

