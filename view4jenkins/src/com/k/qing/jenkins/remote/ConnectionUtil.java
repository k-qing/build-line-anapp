package com.k.qing.jenkins.remote;

public class ConnectionUtil {
	
//	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
//	
//	private static PoolingClientConnectionManager pm = null;
//	
//	static {
//		SchemeRegistry schemeRegistry = new SchemeRegistry();
//		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
//		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
//		pm = new PoolingClientConnectionManager(schemeRegistry);
//        pm.setMaxTotal(400);
//        pm.setDefaultMaxPerRoute(50);
//        
//	}
//	
//	public static HttpClient getHttpClient(String username, String password) throws ClientProtocolException, IOException {
//		DefaultHttpClient client = new DefaultHttpClient(pm);
//		client = (DefaultHttpClient) WebClientDevWrapper.wrapClient(client);
//		
//		HttpGet loginGet = new HttpGet(Common.JENKINS_URL + "loginEntry");
//		client.execute(loginGet);
//		HttpPost loginPost = new HttpPost(Common.JENKINS_URL + "j_acegi_security_check");
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("j_username", username));
//        params.add(new BasicNameValuePair("j_password", password));
//        params.add(new BasicNameValuePair("action", "login"));
//        loginPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
//        HttpResponse httpResponse = client.execute(loginPost);
//        
//        HttpEntity entity = httpResponse.getEntity();  
//        String body = EntityUtils.toString(entity);  
//        System.out.println(body);  
//		
////		// Then provide the right credentials
////		client.getCredentialsProvider().setCredentials(
////				new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
////				new UsernamePasswordCredentials(username, password));
////
////		// Generate BASIC scheme object and stick it to the execution context
////		BasicScheme basicAuth = new BasicScheme();
////		BasicHttpContext context = new BasicHttpContext();
////		context.setAttribute("preemptive-auth", basicAuth);
////		
////		// Add as the first (because of the zero) request interceptor
////		// It will first intercept the request and preemptively initialize the
////		// authentication scheme if there is not
////		client.addRequestInterceptor(new PreemptiveAuth(), 0);
//
//		return client;
//	}
//	
//	public static void rs() throws Exception {
//        HttpClient client = getHttpClient("ejiaqsu", "Beijing1");
//        HttpPost httpPost = new HttpPost(
//                "https://jenkins-mm.mo.sw.ericsson.se/job/bamboo_regression/keepDependencies");
//        // httpPost.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5 * 60
//        // * 1000);
//        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
////        nameValuePairs.add(new BasicNameValuePair("description", "keepDependencies"));
////
////        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
//        HttpResponse httpResponse = client.execute(httpPost);
//
//        HttpEntity entity = httpResponse.getEntity();
//        String body = EntityUtils.toString(entity);
//        System.out.println(body);
//    }
//	
//	public static HttpEntity executeGetRequest(HttpClient httpClient, String url) {
//		HttpGet httpGet = new HttpGet(url);
//		
////		httpPost.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5 * 60 * 1000);
//		HttpEntity httpEntity = null;
//		
//		try {
//			HttpResponse httpResponse = httpClient.execute(httpGet);
//			httpEntity = httpResponse.getEntity();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return httpEntity;
//	}
//	
//	public static String getXmlFromGetRequest(HttpClient httpClient, String url) {
//		HttpEntity httpEntity = executeGetRequest(httpClient, url);
//		if(httpEntity != null) {
//			try {
//				return EntityUtils.toString(httpEntity);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//				try {
//					EntityUtils.consume(httpEntity);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}
//	
//	
//
//	/**
//	 * New a <code>HttpPost</code> object with a given URL.
//	 * Set the time out is 5 minutes.
//	 * 
//	 * @param URL
//	 */
//	private void initHttpPost(String URL) {
////		httpPost = new HttpPost(URL);
////		httpPost.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5 * 60 * 1000);
//	}
//	
//	/**
//	 * Preemptive authentication interceptor
//	 *
//	 */
//	static class PreemptiveAuth implements HttpRequestInterceptor {
//
//		/*
//		 * (non-Javadoc)
//		 *
//		 * @see org.apache.http.HttpRequestInterceptor#process(org.apache.http.HttpRequest,
//		 * org.apache.http.protocol.HttpContext)
//		 */
//		public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
//			// Get the AuthState
//			AuthState authState = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);
//
//			// If no auth scheme available yet, try to initialize it preemptively
//			if (authState.getAuthScheme() == null) {
//				AuthScheme authScheme = (AuthScheme) context.getAttribute("preemptive-auth");
//				CredentialsProvider credsProvider = (CredentialsProvider) context
//						.getAttribute(ClientContext.CREDS_PROVIDER);
//				HttpHost targetHost = (HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
//				if (authScheme != null) {
//					Credentials creds = credsProvider.getCredentials(new AuthScope(targetHost.getHostName(), targetHost
//							.getPort()));
//					if (creds == null) {
//						throw new HttpException("No credentials for preemptive authentication");
//					}
//					authState.setAuthScheme(authScheme);
//					authState.setCredentials(creds);
//				}
//			}
//
//		}
//
//	}
//	
//	
//	public static void main(String[] args) throws Exception {
//		ConnectionUtil.rs();
////		System.out.println(ConnetionUtil.getXmlFromGetRequest("http://147.128.17.152:8080/jenkins/view/SP210-310%20LSV/job/LSV_SWDL/config.xml"));
//	}

}
