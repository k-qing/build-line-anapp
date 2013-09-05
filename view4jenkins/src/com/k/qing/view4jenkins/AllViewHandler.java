package com.k.qing.view4jenkins;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.k.qing.view4jenkins.bean.JenkinsProject;
import com.k.qing.view4jenkins.bean.JenkinsView;
import com.k.qing.view4jenkins.util.JenkinsJsonParser;

public class AllViewHandler extends Handler {
	private final WeakReference<Activity> mActivity;
	
	final ExpandableAdapter expandableAdapter = new ExpandableAdapter();
	
	private List<String> groupData;
	private List<List<String>> childrenData;
	
    public AllViewHandler(Activity activity) {
        mActivity = new WeakReference<Activity>(activity);
    }
    @Override
    public void handleMessage(Message msg) {
		System.out.println(msg);
		Activity activity = mActivity.get();
		if (activity != null) {
			switch (msg.what) {
			case 0:
				AllViewActivity.progressDialog.dismiss();
				activity.setContentView(R.layout.activity_all_view);
				TabHost tabHost = (TabHost)activity.findViewById(R.id.myTabHost);
				
				tabHost.setup();

				tabHost.addTab(tabHost.newTabSpec("tab1")
						.setIndicator("Jobs").setContent(R.id.view1));
				tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("tab2")
						.setContent(R.id.view2));
				tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("tab3")
						.setContent(R.id.view3));
				
				this.initExpandableData();
				
				ExpandableListView expandableListView = (ExpandableListView) activity.findViewById(R.id.mainExpandableListView);
				
				expandableListView.setAdapter(expandableAdapter);
				
//				Button refreshButton = (Button) activity.findViewById(R.id.refreshButton);
//				refreshButton.setOnClickListener(new OnClickListener() {
//					@Override
//					public void onClick(View arg0) {
//						System.out.println("before");
//						new Thread(new UpdateDataRunnable()).start();
//						System.out.println("after");
//					}
//				});
				return;
			default:
				break;
			}
		} else {
			return;
		}
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
				List<JenkinsView> jenkinsViewList = jenkinsJsonParser.getViewList(AllViewActivity.jenkinsURL);
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
			TextView myText = new TextView(mActivity.get());
			myText.setLayoutParams(layoutParams);
			myText.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
			myText.setPadding(80, 0, 0, 0);
			myText.setText(content);
			return myText;
		}

	}
}

