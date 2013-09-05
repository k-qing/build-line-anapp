package com.k.qing.view4jenkins;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.k.qing.view4jenkins.bean.JViewer;
import com.k.qing.view4jenkins.bean.JenkinsProject;
import com.k.qing.view4jenkins.bean.JenkinsView;
import com.k.qing.view4jenkins.util.JenkinsJsonParser;

public class AllViewActivity extends Activity {

	private List<String> groupData;
	private List<List<String>> childrenData;
	
	private JViewer jViewer;
	
	final ExpandableAdapter expandableAdapter = new ExpandableAdapter();
	
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
	
	private void initTabHost() {
		TabHost tabHost = (TabHost) findViewById(R.id.myTabHost);

		this.initExpandableData();

		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.mainExpandableListView);
		
		expandableListView.setAdapter(expandableAdapter);

		
		
		
	}
	
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
	
	

//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_all_view);
//		
//		
//		TabHost tabHost = (TabHost) findViewById(R.id.myTabHost);
//		this.CreateLoginAlert();
//
//		this.initExpandableData();
//
//		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.mainExpandableListView);
//		
//		expandableListView.setAdapter(expandableAdapter);
//
//		// needed
//		tabHost.setup();
//
//		tabHost.addTab(tabHost.newTabSpec("tab1")
//				.setIndicator("tab1 indicator").setContent(R.id.view1));
//		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("tab2")
//				.setContent(R.id.view2));
//		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("tab3")
//				.setContent(R.id.view3));
//		
//		Button refreshButton = (Button) findViewById(R.id.refreshButton);
//		refreshButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				System.out.println("before");
//				new Thread(new UpdateDataRunnable()).start();
//				System.out.println("after");
//			}
//		});
//
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_view, menu);
		return true;
	}

	private void initExpandableData() {
		groupData = new ArrayList<String>();
		childrenData = new ArrayList<List<String>>();
		
		new Thread(new InitDataRunnable()).start();
	}
	
	public void addInfo(String p,String[] c){  
		groupData.add(p);  
          
        List<String> item = new ArrayList<String>();  
          
        for(int i=0;i<c.length;i++){  
            item.add(c[i]);  
        }  
          
        childrenData.add(item);  
    }
	
	private class InitDataRunnable implements Runnable {
		@Override
		public void run() {
			childrenData.clear();
			groupData.clear();
			try {
				JenkinsJsonParser jenkinsJsonParser = new JenkinsJsonParser();
				List<JenkinsView> jenkinsViewList = jenkinsJsonParser.getViewList(jenkinsURL);
				for(JenkinsView jenkinsView : jenkinsViewList) {
					groupData.add(jenkinsView.getName());
					List<JenkinsProject> projectList = jenkinsJsonParser.getProjectListFromView(jenkinsView.getUrl());
					List<String> projectNameList = new ArrayList<String>();
					for(JenkinsProject jenkinsProject : projectList) {
						projectNameList.add(jenkinsProject.getName());
					}
					childrenData.add(projectNameList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update(List<JenkinsView> jenkinsViewList) {
		childrenData.clear();
		groupData.clear();
		try {
			for(JenkinsView jenkinsView : jenkinsViewList) {
				groupData.add(jenkinsView.getName());
				List<JenkinsProject> projectList = jenkinsView.getJenkinsProjectList();
				List<String> projectNameList = new ArrayList<String>();
				for(JenkinsProject jenkinsProject : projectList) {
					projectNameList.add(jenkinsProject.getName());
				}
				childrenData.add(projectNameList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		expandableAdapter.notifyDataSetChanged();
	}
	
	private class UpdateDataRunnable implements Runnable {
		@Override
		public void run() {
			Message message = new Message();
			message.what = 1;
			
			JenkinsJsonParser jenkinsJsonParser = new JenkinsJsonParser();
			List<JenkinsView> jenkinsViewList = jenkinsJsonParser.getViewList(jenkinsURL);
			
			message.obj = jenkinsViewList;
			
			handler.sendMessage(message);
		}
	}

	private class ExpandableAdapter extends BaseExpandableListAdapter {

		@Override
		public Object getChild(int arg0, int arg1) {
			return childrenData.get(arg0).get(arg1);
		}

		@Override
		public long getChildId(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
				ViewGroup arg4) {
			TextView myText = null;
			if (arg3 != null) {
				myText = (TextView) arg3;
				myText.setText(childrenData.get(arg0).get(arg1));
			} else {
				myText = createView(childrenData.get(arg0).get(arg1));
			}
			return myText;
		}

		@Override
		public int getChildrenCount(int arg0) {
			return childrenData.get(arg0).size();
		}

		@Override
		public Object getGroup(int arg0) {
			return groupData.get(arg0);
		}

		@Override
		public int getGroupCount() {
			return groupData.size();
		}

		@Override
		public long getGroupId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getGroupView(int arg0, boolean arg1, View arg2,
				ViewGroup arg3) {
			TextView myText = null;
			if (arg2 != null) {
				myText = (TextView) arg2;
				myText.setText(groupData.get(arg0));
			} else {
				myText = createView(groupData.get(arg0));
			}
			return myText;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return false;
		}

		private TextView createView(String content) {
			AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
					ViewGroup.LayoutParams.FILL_PARENT, 80);
			TextView myText = new TextView(AllViewActivity.this);
			myText.setLayoutParams(layoutParams);
			myText.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			myText.setPadding(80, 0, 0, 0);
			myText.setText(content);
			return myText;
		}

	}
}

