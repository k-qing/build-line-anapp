package com.k.qing.view4jenkins.bean;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import com.k.qing.view4jenkins.util.JenkinsUtil;

public class JViewer {
	
	private String jenkinsURL;
	private String userName;
	private String password;
	
	private HttpClient httpClient;
	
	public JViewer(String jenkinsURL) {
		this.jenkinsURL = jenkinsURL;
	}
	
	public JViewer(String jenkinsURL, String userName, String password) {
		this(jenkinsURL);
		this.userName = userName;
		this.password = password;
	}
	
	public void initConnection() throws ClientProtocolException, IOException {
		this.httpClient = JenkinsUtil.login(jenkinsURL, userName, password);
	}
	
	
	public String getJenkinsURL() {
		return jenkinsURL;
	}

	public void setJenkinsURL(String jenkinsURL) {
		this.jenkinsURL = jenkinsURL;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}
	
}
