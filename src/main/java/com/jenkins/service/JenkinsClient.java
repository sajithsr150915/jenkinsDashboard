package com.jenkins.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class JenkinsClient {

	private static String JenkinsAuthenticationUserName = "admin";
	private static String JenkinsAuthenticationPassword = "admin";
	@Value("${jenkinsURL}")
	private String jenkinsURL;
	
	
	//private   
	

	public  void JenkinsRemoteAPIBuild(String param1, String param2) throws AuthenticationException, ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(
				"http://localhost:8080/job/Parameterized_Job/buildWithParameters?token=e82c6c0878521d55f899f897f4ad07d8&Environment="
						+ param1 + "&Architecture=" + param2);

		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(JenkinsAuthenticationUserName,
				JenkinsAuthenticationPassword);
		httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
		httpPost.addHeader("Content-type", "application/json");
		httpPost.addHeader("Accept", "application/json");
		System.out.println("posting: " + httpPost);
		CloseableHttpResponse response = client.execute(httpPost);
		System.out.println((response.getStatusLine().getStatusCode()));
		System.out.println("Response: " + response);
		client.close();
	}
	
	

	/*private String sendDelete(String url, String projectId) throws IOException { CloseableHttpClient httpClient = HttpClientBuilder.create().build(); //We just change HttpPost > HttpDelete
	HttpDelete deleteRequest = new HttpDelete(String.format(url,projectId)); deleteRequest.addHeader(“accept”, “application/json”);
	HttpResponse response = httpClient.execute(deleteRequest); if (response.getStatusLine().getStatusCode() != 200) {
		throw new RuntimeException(“Failed : HTTP error code : ” + 
				response.getStatusLine().getStatusCode()); } 
	
	BufferedReader br =
				new BufferedReader( new InputStreamReader((response.getEntity().getContent()))); 
				System.out.println(“Output from Server …. n”); StringBuffer result = new StringBuffer(); String output = “”; w
				hile ((output = br.readLine()) != null) { result.append(output); } httpClient.close(); return result.toString(); }

	}*/
	
	
	public  String jenkinsGetAPI(String URI) throws AuthenticationException, ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build(); 
		
		URI = jenkinsURL + URI;
		URI = URI.replace(" ", "%20");
		HttpGet httpGet = new HttpGet( URI);
		
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(JenkinsAuthenticationUserName,
				JenkinsAuthenticationPassword);
		httpGet.addHeader(new BasicScheme().authenticate(creds, httpGet, null));
		httpGet.addHeader("Content-type", "application/json");
		httpGet.addHeader("Accept", "application/json");
		System.out.println("send get request: " + httpGet);
		CloseableHttpResponse response = httpClient.execute(httpGet);
		
		BufferedReader br =
				new BufferedReader( new InputStreamReader((response.getEntity().getContent()))); 
		
		StringBuffer result = new StringBuffer(); String output = "";
				 while ((output = br.readLine()) != null) { result.append(output); }
		
		
		/*System.out.println((response.getStatusLine().getStatusCode()));
		HttpEntity httpEntity = response.getEntity();
		String responseString=new String();
		responseString=(String) String.valueOf(EntityUtils.toString(httpEntity));
		
		System.out.println("Response: " + responseString);*/

		httpClient.close();
		
		return result.toString();
		
	}

	
	
}