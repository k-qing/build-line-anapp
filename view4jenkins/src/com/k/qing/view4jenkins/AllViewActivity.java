package com.k.qing.view4jenkins;

import java.util.ArrayList;
import java.util.List;

import com.k.qing.view4jenkins.bean.JenkinsView;
import com.k.qing.view4jenkins.util.JenkinsJsonParser;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TabHost;
import android.widget.TextView;

public class AllViewActivity extends Activity {

	private List<String> groupData;
	private List<List<String>> childrenData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_view);
		TabHost tabHost = (TabHost) findViewById(R.id.myTabHost);

		this.initExpandableData();

		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.mainExpandableListView);
		expandableListView.setAdapter(new ExpandableAdapter());

		// needed
		tabHost.setup();

		tabHost.addTab(tabHost.newTabSpec("tab1")
				.setIndicator("tab1 indicator").setContent(R.id.view1));
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("tab2")
				.setContent(R.id.view2));
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("tab3")
				.setContent(R.id.view3));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_view, menu);
		return true;
	}

	private void initExpandableData() {
		
		JenkinsJsonParser jenkinsJsonParser = new JenkinsJsonParser();
		List<JenkinsView> jenkinsViewList;
		groupData = new ArrayList<String>();
		try {
			jenkinsViewList = jenkinsJsonParser.getViewList("http://169.254.113.233:8080/jenkins/api/json");
			for(JenkinsView jenkinsView : jenkinsViewList) {
				groupData.add(jenkinsView.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		groupData.add("¹ú¼Ò");
		groupData.add("ÈËÎï");
		groupData.add("ÎäÆ÷");

		childrenData = new ArrayList<List<String>>();
		List<String> Child1 = new ArrayList<String>();
		Child1.add("Êñ¹ú");
		Child1.add("Îº¹ú");
		Child1.add("Îâ¹ú");
		childrenData.add(Child1);
		List<String> Child2 = new ArrayList<String>();
		Child2.add("¹ØÓð");
		Child2.add("ÕÅ·É");
		Child2.add("µäÎ¤");
		Child2.add("ÂÀ²¼");
		Child2.add("²Ü²Ù");
		Child2.add("¸ÊÄþ");
		Child2.add("¹ù¼Î");
		Child2.add("ÖÜè¤");
		childrenData.add(Child2);
		List<String> Child3 = new ArrayList<String>();
		Child3.add("ÇàÁúÙÈÔÂµ¶");
		Child3.add("ÕÉ°ËÉßÃ¬Ç¹");
		Child3.add("Çà¸Ö½£");
		Child3.add("÷è÷ë¹­");
		Child3.add("ÒøÔÂÇ¹");
		childrenData.add(Child3);
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
