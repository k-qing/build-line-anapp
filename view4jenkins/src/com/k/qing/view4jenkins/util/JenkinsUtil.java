package com.k.qing.view4jenkins.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public final class JenkinsUtil {
	
	private JenkinsUtil() {
		
	}
	
	public static HttpClient login(String jenkinsURL, String username, String password) throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		
		HttpGet loginGet = new HttpGet(jenkinsURL + "loginEntry");
		httpClient.execute(loginGet);
		HttpPost loginPost = new HttpPost(jenkinsURL + "j_acegi_security_check");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("j_username", username));
        params.add(new BasicNameValuePair("j_password", password));
        params.add(new BasicNameValuePair("action", "login"));
        loginPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
//        HttpResponse httpResponse = httpClient.execute(loginPost);
		
		return httpClient;
	}
}
