package com.k.qing.view4jenkins.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class JenkinsJsonParser {

	public static void main(String[] args) throws Exception {
		// every Hudson model object exposes the .../api/xml, but in this
		// example
		// we'll just take the root object as an example
		String url = "http://127.0.0.1:8080:jenkins/api/xml";

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
	
	public void a() throws Exception {
		// we'll just take the root object as an example
				String url = "http://127.0.0.1:8080/jenkins/api/json";

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
