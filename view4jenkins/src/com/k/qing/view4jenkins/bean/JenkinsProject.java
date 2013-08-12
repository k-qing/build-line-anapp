package com.k.qing.view4jenkins.bean;

public class JenkinsProject {
	private String name;
	private String url;
	private String downStreamProject;
	
	public JenkinsProject(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDownStreamProject() {
		return downStreamProject;
	}
	public void setDownStreamProject(String downStreamProject) {
		this.downStreamProject = downStreamProject;
	}
}
