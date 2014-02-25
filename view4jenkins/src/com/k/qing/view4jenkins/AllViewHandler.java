package com.k.qing.view4jenkins;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.k.qing.view4jenkins.bean.JenkinsView;
import com.k.qing.view4jenkins.util.JenkinsJsonParser;

public class AllViewHandler extends Handler {
	private final WeakReference<Activity> mActivity;
	
	private static List<Map<String, Object>> mData = new ArrayList<Map<String,Object>>();
	
	private MyAdapter listAdapter = null;
	private ListView expandableListView = null;
	
    public AllViewHandler(Activity activity) {
        mActivity = new WeakReference<Activity>(activity);
    }
    
    @Override
    public void handleMessage(Message msg) {
		System.out.println(msg);
		Activity activity = mActivity.get();
		if (activity != null) {
			listAdapter = new MyAdapter(activity);
			
			switch (msg.what) {
			case 0:
				if(AllViewActivity.progressDialog != null) {
					AllViewActivity.progressDialog.dismiss();
				}
				activity.setContentView(R.layout.activity_all_view);
				
				ActionBar bar = activity.getActionBar();
				bar.setDisplayHomeAsUpEnabled(true); 
//				bar.setTitle("Try tab action bar");
				bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
				
				AllViewTabListener tl = new AllViewTabListener();
				
				Tab tab1 = bar.newTab();
		    	tab1.setText("Jenkins View");
		    	tab1.setTabListener(tl);
		    	bar.addTab(tab1);
		    	
		    	this.expandableListView = (ListView) activity.findViewById(R.id.mainListView);
		    	this.expandableListView.setAdapter(listAdapter);
				return;
			case 1:
				System.out.println(mData);
				this.expandableListView.setAdapter(listAdapter);
				listAdapter.notifyDataSetChanged();
				return;
			default:
				break;
			}
		} else {
			return;
		}
    }
    
    public static void refreshData(List<Map<String, Object>> data) {
    	mData.clear();
    	mData.addAll(data);
    }
    
	
	public final class ViewHolder {
		public TextView name;
	}
	
	public class MyAdapter extends BaseAdapter{
		 
        private LayoutInflater mInflater;
         
         
        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return mData.size();
        }
 
        @Override
        public Object getItem(int arg0) {
            return null;
        }
 
        @Override
        public long getItemId(int arg0) {
            return 0;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
             
            ViewHolder holder = null;
            if (convertView == null) {
                 
                holder=new ViewHolder();  
                 
                convertView = mInflater.inflate(R.layout.list_item, null);
                holder.name = (TextView)convertView.findViewById(R.id.name);
                convertView.setTag(holder);
                 
            }else {
                holder = (ViewHolder)convertView.getTag();
            }
             
            holder.name.setText((String)mData.get(position).get("name"));
            return convertView;
        }
    }
}

