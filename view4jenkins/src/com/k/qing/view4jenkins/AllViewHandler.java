package com.k.qing.view4jenkins;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private List<Map<String, Object>> mData = new ArrayList<Map<String,Object>>();
	
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
				ListView expandableListView = (ListView) activity.findViewById(R.id.mainListView);
				MyAdapter listAdapter = new MyAdapter(activity);
				expandableListView.setAdapter(listAdapter);
				this.initExpandableData();
				return;
			default:
				break;
			}
		} else {
			return;
		}
    }
    
    private void initExpandableData() {
		new Thread(new InitDataRunnable()).start();
	}
	
	private class InitDataRunnable implements Runnable {
		@Override
		public void run() {
			if(mData == null) {
				mData = new ArrayList<Map<String,Object>>();
			} else {
				mData.clear();
			}
			
			try {
				JenkinsJsonParser jenkinsJsonParser = new JenkinsJsonParser();
				List<JenkinsView> jenkinsViewList = jenkinsJsonParser.getViewList(AllViewActivity.jenkinsURL);
				for(JenkinsView jenkinsView : jenkinsViewList) {
					Map<String, Object> viewMap = new HashMap<String, Object>();
					viewMap.put("name", jenkinsView.getName());
					mData.add(viewMap);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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

