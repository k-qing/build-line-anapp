package com.k.qing.view4jenkins.bean;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class JenkinsView implements Parcelable {
	private String name;
	private String url;
	private List<JenkinsProject> jenkinsProjectList;
	
	public JenkinsView(String name, String url) {
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
	
	public List<JenkinsProject> getJenkinsProjectList() {
		return jenkinsProjectList;
	}

	public void setJenkinsProjectList(List<JenkinsProject> jenkinsProjectList) {
		this.jenkinsProjectList = jenkinsProjectList;
	}

	public JenkinsView(Parcel in) {
		this.name = in.readString();
		this.url = in.readString();
		in.readTypedList(this.jenkinsProjectList, JenkinsProject.CREATOR);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(this.name);
		out.writeString(this.url);
		out.writeTypedList(this.jenkinsProjectList);
	}
	
	public static final Parcelable.Creator<JenkinsView> CREATOR = new Creator<JenkinsView>() {

		@Override
		public JenkinsView createFromParcel(Parcel in) {
			return new JenkinsView(in);
		}

		@Override
		public JenkinsView[] newArray(int size) {
			return new JenkinsView[size];
		}  
        
    };
	
}
