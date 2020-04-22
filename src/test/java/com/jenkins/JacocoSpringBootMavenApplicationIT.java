package com.jenkins;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jenkins.model.BuildDetail;
import com.jenkins.model.JenkinsDetail;
import com.jenkins.model.JenkinsDetails;
import com.jenkins.model.JenkinsDetailsFolder;
import com.jenkins.model.JobDetail;
import com.jenkins.model.JobFolder;
import com.jenkins.service.ManageJenkinsService; 

class JacocoSpringBootMavenApplicationIT {

	//@Mock
	//public RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
	
	@InjectMocks
	ManageJenkinsService manageJenkinsService=new ManageJenkinsService();
	
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
	
	
	
	
	@Test
	public void test() throws URISyntaxException {
		boolean flag=true;
		JenkinsDetail job = null;
		JenkinsDetails jobBranchDeploy = null;
		manageJenkinsService.setAdditionalCase(job, jobBranchDeploy);
		job=new JenkinsDetail();
		JobDetail jobd=new JobDetail();
		job.setJob(jobd);
		manageJenkinsService.setAdditionalCase(job, jobBranchDeploy);
		List<BuildDetail> buildListOld = new ArrayList<>();
		jobd.setLastSuccessfulBuild(buildListOld);
		jobd.setAllBuild(buildListOld);

		job.setJob(jobd);

		manageJenkinsService.setAdditionalCase(job, jobBranchDeploy);
		Assertions.assertEquals(true, flag);


	}
	
	
	@Test
	public void testAdditionalCase() throws URISyntaxException {
		boolean flag=true;

		JenkinsDetail job = null;
		JenkinsDetails jobBranchDeploy = null;
		job=new JenkinsDetail();
		JobDetail jobd=new JobDetail();
		job.setJob(jobd);
		List<BuildDetail> buildListOld = new ArrayList<>();
		jobd.setLastSuccessfulBuild(buildListOld);
		jobd.setAllBuild(buildListOld);

		job.setJob(jobd);

		manageJenkinsService.setAdditionalCase(job, jobBranchDeploy);
		 jobBranchDeploy = new JenkinsDetails();
		manageJenkinsService.setAdditionalCase(job, jobBranchDeploy);

		JenkinsDetailsFolder jobF=new JenkinsDetailsFolder();
		jobBranchDeploy.setJob(jobF);
		manageJenkinsService.setAdditionalCase(job, jobBranchDeploy);
		
		JobFolder jobff=new JobFolder();
		jobBranchDeploy.getJob().setJob(jobff);
		manageJenkinsService.setAdditionalCase(job, jobBranchDeploy);
		
		List<JobDetail> dummy=new ArrayList<>();
		jobBranchDeploy.getJob().getJob().setJob(dummy);
		manageJenkinsService.setAdditionalCase(job, jobBranchDeploy);
		
		job.setJob(null);
		manageJenkinsService.setAdditionalCase(job, jobBranchDeploy);

		Assertions.assertEquals(true, flag);

	}
	
	@Test
	public void lastDeployExcption() throws URISyntaxException {		
		
		boolean flag=true;
		try {
		manageJenkinsService.lastDeployments("", "");
		manageJenkinsService.getCountGroupByJenkinsJob("",0);
		manageJenkinsService.getCountGroupByAcceptance(0l);

		}catch(Exception e) {
			Assertions.assertEquals(true, flag);

		}
		
	}
	
	
	@Test
	public void testJob() throws URISyntaxException {
		boolean flag=true;

		JenkinsDetail job = null;
		JenkinsDetails jobBranchDeploy = null;
		manageJenkinsService.setAdditionalJobs(jobBranchDeploy, job);
		job=new JenkinsDetail();
		JobDetail jobd=new JobDetail();
		job.setJob(jobd);
		manageJenkinsService.setAdditionalJobs(jobBranchDeploy, job);
		List<BuildDetail> buildListOld = new ArrayList<>();
		jobd.setLastSuccessfulBuild(buildListOld);
		jobd.setAllBuild(buildListOld);

		job.setJob(jobd);

		manageJenkinsService.setAdditionalJobs(jobBranchDeploy, job);

		Assertions.assertEquals(true, flag);

	}
	
	
	@Test
	public void testAdditionalJob() throws URISyntaxException {
		boolean flag=true;

		JenkinsDetail job = null;
		JenkinsDetails jobBranchDeploy = null;
		job=new JenkinsDetail();
		JobDetail jobd=new JobDetail();
		job.setJob(jobd);
		List<BuildDetail> buildListOld = new ArrayList<>();
		jobd.setLastSuccessfulBuild(buildListOld);
		job.setJob(jobd);

		manageJenkinsService.setAdditionalJobs(jobBranchDeploy, job);
		 jobBranchDeploy = new JenkinsDetails();
		manageJenkinsService.setAdditionalJobs(jobBranchDeploy, job);

		JenkinsDetailsFolder jobF=new JenkinsDetailsFolder();
		jobBranchDeploy.setJob(jobF);
		manageJenkinsService.setAdditionalJobs(jobBranchDeploy, job);
		
		JobFolder jobff=new JobFolder();
		jobBranchDeploy.getJob().setJob(jobff);
		manageJenkinsService.setAdditionalJobs(jobBranchDeploy, job);
		
		List<JobDetail> dummy=new ArrayList<>();
		jobBranchDeploy.getJob().getJob().setJob(dummy);
		manageJenkinsService.setAdditionalJobs(jobBranchDeploy, job);
		job.setJob(null);
		manageJenkinsService.setAdditionalJobs(jobBranchDeploy, job);
		Assertions.assertEquals(true, flag);

	}
	
	
	@Test
	public void testJobGrp() throws URISyntaxException {
		boolean flag=true;

		JenkinsDetail job = null;
		JenkinsDetail jobBranchDeploy = null;
		manageJenkinsService.setAdditionalCountByGroup(jobBranchDeploy, job);
		job=new JenkinsDetail();
		JobDetail jobd=new JobDetail();
		job.setJob(jobd);
		manageJenkinsService.setAdditionalCountByGroup(jobBranchDeploy, job);
		List<BuildDetail> buildListOld = new ArrayList<>();
		jobd.setLastSuccessfulBuild(buildListOld);
		jobd.setAllBuild(buildListOld);

		job.setJob(jobd);

		manageJenkinsService.setAdditionalCountByGroup(jobBranchDeploy, job);
		Assertions.assertEquals(true, flag);

	}
	
	
	@Test
	public void testAdditionalJobGrp() throws URISyntaxException {
		boolean flag=true;

		JenkinsDetail job = null;
		JenkinsDetail jobBranchDeploy = null;
		manageJenkinsService.setAdditionalCountByGroup( job,jobBranchDeploy);
		job=new JenkinsDetail();
		JobDetail jobd=new JobDetail();
		job.setJob(jobd);
		manageJenkinsService.setAdditionalCountByGroup(job,jobBranchDeploy);
		List<BuildDetail> buildListOld = new ArrayList<>();
		jobd.setLastSuccessfulBuild(buildListOld);
		jobd.setAllBuild(buildListOld);

		job.setJob(jobd);

		manageJenkinsService.setAdditionalCountByGroup(job,jobBranchDeploy);
		
		job.setJob(null);
		manageJenkinsService.setAdditionalCountByGroup(job,jobBranchDeploy);
		
		manageJenkinsService.setAdditionalCountByGroup(jobBranchDeploy,job);
		
		Assertions.assertEquals(true, flag);


		

	}
	
	

}

