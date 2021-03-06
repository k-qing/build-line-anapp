package com.k.qing.view4jenkins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.k.qing.view4jenkins.bean.JViewer;
import com.k.qing.view4jenkins.bean.JenkinsView;
import com.k.qing.view4jenkins.preference.Const;
import com.k.qing.view4jenkins.preference.SettingsActivity;
import com.k.qing.view4jenkins.util.JenkinsJsonParser;

public class AllViewActivity extends Activity {

	private static JViewer jViewer;
	
	public static ProgressDialog progressDialog;
	
	public static String JENKINS_URL = "http://125.33.125.19:8080/jenkins/";
	
	private Handler progressDialogHandler = new AllViewHandler(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences myPrefs = getPreferences(MODE_PRIVATE);
		String prefsJenkinsURL = myPrefs.getString(Const.PREFS_JENKINS_URL, Const.PREFS_JENKINS_URL);
		
//		if (!prefsJenkinsURL.equals(Const.PREFS_JENKINS_URL)) {
//			JENKINS_URL = prefsJenkinsURL;
//			new Thread() {
//				@Override
//				public void run() {
//					try {
//						jViewer = new JViewer(JENKINS_URL, "", "");
//						jViewer.initConnection();
//						try {
//							List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
//							JenkinsJsonParser jenkinsJsonParser = new JenkinsJsonParser();
//							List<JenkinsView> jenkinsViewList = jenkinsJsonParser.getViewList(JENKINS_URL);
//							for(JenkinsView jenkinsView : jenkinsViewList) {
//								Map<String, Object> viewMap = new HashMap<String, Object>();
//								viewMap.put("name", jenkinsView.getName());
//								data.add(viewMap);
//							}
//							AllViewHandler.refreshData(data);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//						progressDialogHandler.sendEmptyMessage(0);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//				
//			}.start();
//			
//		} else {
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
							String jenkinsURL = editTextJenkinsURL.getText().toString();
							
							JENKINS_URL = jenkinsURL;
							SharedPreferences myPrefs = getPreferences(MODE_PRIVATE);
							Editor editor = myPrefs.edit();
							editor.putString(Const.PREFS_JENKINS_URL, jenkinsURL);
							editor.commit();
							
							try {
								jViewer = new JViewer(jenkinsURL, "", "");
								jViewer.initConnection();
								try {
									List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
									JenkinsJsonParser jenkinsJsonParser = new JenkinsJsonParser();
									List<JenkinsView> jenkinsViewList = jenkinsJsonParser.getViewList(AllViewActivity.JENKINS_URL);
									for(JenkinsView jenkinsView : jenkinsViewList) {
										Map<String, Object> viewMap = new HashMap<String, Object>();
										viewMap.put("name", jenkinsView.getName());
										data.add(viewMap);
									}
									AllViewHandler.refreshData(data);
								} catch (Exception e) {
									e.printStackTrace();
								}
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
//		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(progressDialog != null) {
			progressDialog.dismiss();
		}
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
			System.out.println("sssssssssssssssssssssssssssssssssssssssss");
			return true;
		} else if (item.getItemId() == R.id.menu_icon_refresh) {
			new Thread() {
				@Override
				public void run() {
					try {
						List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
						JenkinsJsonParser jenkinsJsonParser = new JenkinsJsonParser();
						List<JenkinsView> jenkinsViewList = jenkinsJsonParser
								.getViewList(JENKINS_URL);
						for (JenkinsView jenkinsView : jenkinsViewList) {
							Map<String, Object> viewMap = new HashMap<String, Object>();
							viewMap.put("name", jenkinsView.getName());
							data.add(viewMap);
						}
						AllViewHandler.refreshData(data);
						progressDialogHandler.sendEmptyMessage(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
			
			return true;
		} else if (item.getItemId() == R.id.menu_icon_settings) {
			Intent intent = new Intent(this, SettingsActivity.class);
			this.startActivity(intent);
			return true;
		}

		return false;
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

