package com.k.qing.view4jenkins.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class JenkinsProject implements Parcelable {
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
	
	public JenkinsProject(Parcel in) {
		this.name = in.readString();
		this.url = in.readString();
		this.downStreamProject = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeString(this.url);
		dest.writeString(this.downStreamProject);
	}
	
	public static final Parcelable.Creator<JenkinsProject> CREATOR = new Creator<JenkinsProject>() {

		@Override
		public JenkinsProject createFromParcel(Parcel in) {
			return new JenkinsProject(in);
		}

		@Override
		public JenkinsProject[] newArray(int size) {
			return new JenkinsProject[size];
		}  
        
    };
}
