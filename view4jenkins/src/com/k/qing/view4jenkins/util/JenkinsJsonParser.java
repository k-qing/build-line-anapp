package com.k.qing.view4jenkins.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.k.qing.view4jenkins.bean.JenkinsProject;
import com.k.qing.view4jenkins.bean.JenkinsView;

public class JenkinsJsonParser {

	public List<JenkinsView> getViewList(String jenkinsUrl) {
		
		List<JenkinsView> jenkinsViewList = new ArrayList<JenkinsView>();
		String url = jenkinsUrl;
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		HttpResponse response;
		try {
			response = httpClient.execute(request);
			String result = EntityUtils.toString(response.getEntity());
			JSONObject object = new JSONObject(result);
			JSONArray jsonArray = object.getJSONArray("views"); 
            for(int i=0;i<jsonArray.length();i++){ 
                JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
                
                String viewName = jsonObject2.getString("name");
                String viewUrl = jsonObject2.getString("url");
                
                JenkinsView jenkinsView = new JenkinsView(viewName, viewUrl);
                
                if(viewUrl != null) {
                	List<JenkinsProject> projectList = this.getProjectListFromView(viewUrl);
                	jenkinsView.setJenkinsProjectList(projectList);
                }
                
                
                jenkinsViewList.add(jenkinsView);
            }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jenkinsViewList;
	}
	
	public List<JenkinsProject> getProjectListFromView(String viewUrl) {
		
		viewUrl = viewUrl + "/api/json/";
		
		List<JenkinsProject> projectList = new ArrayList<JenkinsProject>();
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost request = new HttpPost(viewUrl);
		HttpResponse response;
		try {
			response = httpClient.execute(request);
			String result = EntityUtils.toString(response.getEntity());
			JSONObject object = new JSONObject(result);
			JSONArray jsonArray = object.getJSONArray("jobs"); 
            for(int i=0;i<jsonArray.length();i++){ 
                JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
                
                String jobName = jsonObject2.getString("name");
                String jobUrl = jsonObject2.getString("url");
                
                JenkinsProject jenkinsProject = new JenkinsProject(jobName, jobUrl);
                projectList.add(jenkinsProject);
            }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return projectList;
	}
	
	public void a() throws Exception {
		// we'll just take the root object as an example
				String url = "http://10.0.2.2:8080/jenkins/api/json";

				// if you are calling security-enabled Hudson and
				// need to invoke operations and APIs that are protected,
				// consult the 'SecuredMain" class
				// in this package for an example using HttpClient.

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet request = new HttpGet(url);
				HttpResponse response = httpClient.execute(request);
				String result = EntityUtils.toString(response.getEntity());
				JSONObject object = new JSONObject(result);
				System.out.println(object);
	}

}
