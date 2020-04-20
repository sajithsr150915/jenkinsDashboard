package com.jenkins;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jenkins.controller.ManageJenkinsController;
import com.jenkins.model.Deployment;
import com.jenkins.service.JenkinsCountDetail;
import com.jenkins.service.ManageJenkinsService;
import com.jenkins.service.TestJobCountDetail;
import com.jenkins.service.TestJobDetail; 

class JacocoSpringBootMavenApplicationIT {

	//@Mock
	public RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
	
	@InjectMocks
	ManageJenkinsService manageJenkinsService=new ManageJenkinsService(restTemplateBuilder); 
	
    public static final int COUNT_ZER0=0;
    
    
    @InjectMocks
    ManageJenkinsController manageJenkinsController=new ManageJenkinsController();
	

	
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
		JenkinsDetails jenkinsDetails=new JenkinsDetails();
		manageJenkinsService=Mockito.mock(ManageJenkinsService.class);
		Mockito.doReturn(jenkinsDetails).when(manageJenkinsService).getJenkinsDetails();
		Assertions.assertEquals(manageJenkinsController.getCountDetailsofJenkins().getTotalCount(), COUNT_ZER0);
		
		
	}*/

  
     
	@Test
	public void testgetCountDetailsofJenkins() throws URISyntaxException {
		

		JenkinsCountDetail jenkinsCountDetail =manageJenkinsService.getCountDetailsofJenkins(null);
		Assertions.assertEquals(COUNT_ZER0,jenkinsCountDetail.getTotalCount());
	
	}
	
	@Test
	public void testgetCountDetailsofJobs() throws URISyntaxException {
		

		Map<String,JenkinsCountDetail> map =manageJenkinsService.getCountDetailsofJobs();
		Assertions.assertEquals(COUNT_ZER0,map.size());
	
	}
	
	@Test
	public void testuatDeployments() throws URISyntaxException {
		

		JenkinsCountDetail countDetail =manageJenkinsService.uatDeployments();
		Assertions.assertEquals( COUNT_ZER0,countDetail.getTotalCount());
	
	}
	
	
	@Test
	public void testprodDeployments() throws URISyntaxException {
		

		JenkinsCountDetail countDetail =manageJenkinsService.prodDeployments();
		Assertions.assertEquals(COUNT_ZER0,countDetail.getTotalCount());
	
	}
	
/*	@Test
	public void testlastSuccesfulUAT() throws URISyntaxException {
		

		List<Deployment> list =manageJenkinsService.lastSuccesfulUAT();
		Assertions.assertEquals(list.size(), COUNT_ZER0);
	
	}*/
	
	
	
	@Test
	public void testlastSuccesfulPROD() throws URISyntaxException {
		

		List<Deployment> list =manageJenkinsService.lastSuccesfulPROD();
		Assertions.assertEquals(COUNT_ZER0,list.size());
	
	}
	
	
	@Test
	public void testacceptanceTest() throws URISyntaxException {
		

		TestJobCountDetail countDetail =manageJenkinsService.acceptanceTest();
		Assertions.assertEquals(COUNT_ZER0,countDetail.getTotalCount());
	
	}
	
	
	@Test
	public void testacceptanceTestGroup() throws URISyntaxException {
		

		List<TestJobDetail>list =manageJenkinsService.acceptanceTestGroup();
		Assertions.assertEquals(COUNT_ZER0,list.size());
	
	}
	
	
	
	@Test
	public void testgetAllBuildsCountbyDate() throws URISyntaxException {
		

		JenkinsCountDetail countDetail =manageJenkinsService.getAllBuildsCountbyDate(1);
		Assertions.assertEquals(COUNT_ZER0,countDetail.getTotalCount()); 
	
	}
	
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
	

}

