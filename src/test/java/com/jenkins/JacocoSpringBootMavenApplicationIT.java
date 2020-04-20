package com.jenkins;


import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jenkins.service.ManageJenkinsService; 

class JacocoSpringBootMavenApplicationIT {

	//@Mock
	public RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
	
	@InjectMocks
	ManageJenkinsService manageJenkinsService=new ManageJenkinsService(restTemplateBuilder); 
	
    public static final int COUNT_ZER0=0;
    
    
    
	

	// for new controller
	
	@Test
	public void countGroupByAcceptancTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/countGroupByAcceptancTest";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	@Test
	public void countGroupByAcceptancTestLimit() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/countGroupByAcceptancTest/20";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void countGroupByJenkinsJob() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/countGroupByJenkinsJob";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void countGroupByJenkinsJobLimit() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/countGroupByJenkinsJob/20";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void countGroupByPROD() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/countGroupByPROD";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void countGroupByPRODLimit() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/countGroupByPROD/20";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void countGroupByUAT() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/countGroupByUAT";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void countGroupByUATLimit() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/countGroupByUAT/20";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void totalBuildCountJenkins() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/totalBuildCountJenkins";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void totalBuildCountJenkinsLimit() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/totalBuildCountJenkins/20";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void totalBuildCountPROD() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/totalBuildCountPROD";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void totalBuildCountPRODLimit() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/totalBuildCountPROD/20";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void totalBuildCountUAT() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/totalBuildCountUAT";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	@Test
	public void totalBuildCountUATLimit() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/totalBuildCountUAT/20";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void totalCountAcceptance() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/totalCountAcceptance";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void totalCountAcceptanceLimit() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/totalCountAcceptance/20";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	

	
	@Test
	public void lastUATDeployments() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/lastUATDeployments";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void lastPRODDeployments() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsJob/lastPRODDeployments";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void jenkinswithTestReport() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinswithTestReport";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	@Test
	public void jenkinsDetails() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "/jenkinsDetails";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	
	
	
	
	

}

