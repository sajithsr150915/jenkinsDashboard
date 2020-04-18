package com.jenkins;


import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jenkins.service.ManageJenkinsService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class JacocoSpringBootMavenApplicationIT {

	
	
	@Mock
	ManageJenkinsService manageJenkinsService;
	
	@Test
	public void jenkinsBuildDetails() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/jenkinsBuildDetails";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void jobBuildDetails() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/jobBuildDetails";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void uatDeployment() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/uatDeployment";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	@Test
	public void prodDeployment() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/prodDeployment";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	@Test
	public void lastSuccesfulUAT() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/lastSuccesfulUAT";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	@Test
	public void lastSuccesfulPROD() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/lastSuccesfulPROD";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	@Test
	public void acceptanceTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/acceptanceTest";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	@Test
	public void acceptanceTestGroup() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/acceptanceTestGroup";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	@Test
	public void jenkinsBuildCount() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/jenkinsBuildCount/1";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void citBuildCount() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/citBuildCount/1";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void prodBuildCount() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/prodBuildCount/1";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void jenkinsDetails() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "jenkinsDetails";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void jenkinswithTestReport() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "jenkinswithTestReport";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	

	/*@Test
	public void testgetCountDetailsofJenkins() throws URISyntaxException {
		

		when(manageJenkinsService.getJenkinsDetails()).thenReturn(null);
		Assertions.assertEquals(manageJenkinsService.getCountDetailsofJenkins(null).getTotalCount(), 0);
		
		
	}*/
	
	
	
	

}

