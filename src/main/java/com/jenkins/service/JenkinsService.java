package com.jenkins.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.jenkins.constants.Constants;

@Service
@Component
public class JenkinsService {

	private final RestTemplate restTemplate;		
	@Value("${jenkinsURL}")
	private String url;
	@Value("${userName}")
	private  String usrname = "admin";
	@Value("${password}")
	private  String pswd = "admin";
	

	
	public JenkinsService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder
				.basicAuthentication(usrname, pswd)
				.build();
	}
		
	public String getJenkinsDetails() {
		
		String jenkinsUrl = this.url + Constants.JENKINS_DETAILS_API_URL;
		return this.restTemplate.getForObject(jenkinsUrl, String.class); 
	}
	
	
			
		
	
}
