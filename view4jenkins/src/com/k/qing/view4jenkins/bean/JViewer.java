package com.k.qing.view4jenkins.bean;

public class JViewer {
	
	private String jenkinsURL;
	private String userName;
	private String password;
	
	public JViewer(String jenkinsURL) {
		this.jenkinsURL = jenkinsURL;
	}
	
	public JViewer(String userName, String password) {
		this.userName = userName;
		this.password = password;
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
}
