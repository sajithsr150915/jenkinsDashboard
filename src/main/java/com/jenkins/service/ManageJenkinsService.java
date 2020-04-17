package com.jenkins.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.jenkins.constants.Constants;
import com.jenkins.model.Action;
import com.jenkins.model.Build;
import com.jenkins.model.Deployment;
import com.jenkins.model.JenkinsDetails;
import com.jenkins.model.Job;


@Service
@Component
public class ManageJenkinsService {

	private final RestTemplate restTemplate;		
	@Value("${jenkinsURL}")
	private String url;
	@Value("${userName}")
	private  String usrname = "admin";
	@Value("${password}")
	private  String pswd = "admin";
	

	
	public ManageJenkinsService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder
				.basicAuthentication(usrname, pswd)
				.build();
	}
		
	public JenkinsDetails getJenkinsDetails() {
		
		String jenkinsUrl = this.url + Constants.JENKINS_DETAILS_API_URL; 
		return this.restTemplate.getForObject(jenkinsUrl, JenkinsDetails.class);
		
	}
	
	public JenkinsCountDetail uatDeployments() {
		return getCountDetailsofJenkins(Constants.UAT_DEPLOYMENT);

	}
	
	public JenkinsCountDetail prodDeployments() {
		return getCountDetailsofJenkins(Constants.PROD_DEPLOYMENT);

	}
	
	public JenkinsCountDetail getCountDetailsofJenkins(String jobName) {

		JenkinsDetails jenkinsDetails = getJenkinsDetails();
		JenkinsCountDetail jenkinsCountDetail = new JenkinsCountDetail();
		Integer totalCount = 0;
		Integer successCount = 0;
		if (null != jenkinsDetails) {
			List<Job> jobList = jenkinsDetails.getJobs();

			if (!jobList.isEmpty()) {

				for (Job job : jobList) {
						if (!(jobName == null || job.getName().contains(jobName))) {
						continue;
						}
					
					List<Build> buildList = job.getBuilds();
					if (!buildList.isEmpty()) {
						successCount=findBuildCount(buildList,  successCount);
						totalCount=totalCount+buildList.size();
					}
				}
			}

			Integer failureCount = totalCount - successCount;
			setCountOfBuilds(jenkinsCountDetail, totalCount, successCount, failureCount);

		}
		return jenkinsCountDetail;

	}
	
	public List<Deployment> lastSuccesfulUAT() {
		
		// previously used lastSuccesfulRun method
		
		return lastSuccesfulRelease(Constants.UAT_DEPLOYMENT);
		
	}
	
	
	public List<Deployment> lastSuccesfulPROD() {
		return lastSuccesfulRun(Constants.PROD_DEPLOYMENT,Constants.SUCESS);

	}
	
	
	public List<Deployment> lastSuccesfulRun(String jobName,String status) {

		JenkinsDetails jenkinsDetails = getJenkinsDetails();
	    List<Deployment> deploymentList=new ArrayList<>();
		if (null != jenkinsDetails) {
			List<Job> jobList = jenkinsDetails.getJobs();

			if (!jobList.isEmpty()) {

				for (Job job : jobList) {
						if (! (jobName == null || job.getName().contains(jobName))) {
							continue;
						}
					Deployment deployment=new Deployment();
					List<Build> buildList = job.getBuilds();
					if (!buildList.isEmpty()) {
						findSuccesfulBuild(buildList, deployment, job,status);
						deploymentList.add(deployment); 
					}
				}
			}
		}
		return deploymentList;

	}
	
	

	private void setCountOfBuilds(JenkinsCountDetail jenkinsCountDetail, Integer totalCount, Integer successCount,
			Integer failureCount) {
		jenkinsCountDetail.setFailureCount(failureCount);
		jenkinsCountDetail.setSuccessCount(successCount);
		jenkinsCountDetail.setTotalCount(totalCount);
	}
	
	
	public Map<String,JenkinsCountDetail> getCountDetailsofJobs() {

		JenkinsDetails jenkinsDetails = getJenkinsDetails();
		Map<String,JenkinsCountDetail> jenkinsCountMap=new LinkedHashMap<>();
		if (null != jenkinsDetails) {
			List<Job> jobList = jenkinsDetails.getJobs();

			if (!jobList.isEmpty()) {

				for (Job job : jobList) {
					Integer totalCount = 0;
					Integer successCount = 0;
					JenkinsCountDetail jenkinsCountDetail = new JenkinsCountDetail();

					List<Build> buildList = job.getBuilds();
					if (!buildList.isEmpty()) {
						successCount=  findBuildCount(buildList, successCount);
						totalCount=totalCount+buildList.size();

					}
					
					Integer failureCount = totalCount - successCount;
					setCountOfBuilds(jenkinsCountDetail, totalCount, successCount, failureCount);
					jenkinsCountMap.put(job.getName(), jenkinsCountDetail);
					
				}
			}
		}
		return jenkinsCountMap;

	}
	

	
	
	

	private Integer findBuildCount(List<Build> buildList,  Integer successCount) {
		for(Build build:buildList) {
			
			  if(Constants.SUCESS.equalsIgnoreCase(build.getResult())) {
				  successCount=successCount+1; 
			  }
			   
		  }
		return successCount;
	}
	
	
	private void findSuccesfulBuild(List<Build> buildList,Deployment deployment,Job job,String status) {
		for(Build build:buildList) {

			  if(status.equalsIgnoreCase(build.getResult())) {
				  deployment.setLastSuccessfulRun(build.getTimestamp());
				  deployment.setJobName(job.getName());
				  break;
			  }
			   
		  }
	}	
	
	
	public JenkinsDetails jenkinswithTestReport() {

		String jenkinsUrl = this.url + Constants.JENKINS_TESTDETAILS_API_URL;
		return this.restTemplate.getForObject(jenkinsUrl, JenkinsDetails.class);
	}
	
	public TestJobCountDetail acceptanceTest() {
		return getTestCaseCount(Constants.ACCEPTANCE);
	}
	
	
	public TestJobCountDetail getTestCaseCount(String jobName) {

		JenkinsDetails jenkinsDetails = jenkinswithTestReport();
		TestJobCountDetail testJobCountDetail = new TestJobCountDetail();
		if (null != jenkinsDetails) {
			List<Job> jobList = jenkinsDetails.getJobs();

			if (!jobList.isEmpty()) {

				for (Job job : jobList) {
						if (!(jobName == null || job.getName().contains(jobName))) {
						continue;
						}
					
					List<Build> buildList = job.getBuilds();
					if (!buildList.isEmpty()) {
						findTestCaseCount(buildList,  testJobCountDetail);
					}
				}
			}

			

		}
		return testJobCountDetail;

	}
	
	private TestJobCountDetail findTestCaseCount(List<Build> buildList,  TestJobCountDetail testJobCountDetail) {
		for(Build build:buildList) {
		List<Action> actionList=build.getActions();
		if(!actionList.isEmpty()) {
			for(Action action:actionList) {
				if(Constants.TEST_REPORT_URL.equalsIgnoreCase(action.getUrlName())) {
					int failureCount=action.getFailCount();
					int skippedCount=action.getSkipCount();
					int totalCount=action.getTotalCount();
					int successCount=totalCount-(failureCount+skippedCount);
					
					testJobCountDetail.setFailureCount(testJobCountDetail.getFailureCount()+failureCount);
					testJobCountDetail.setSkippedCount(testJobCountDetail.getSkippedCount()+skippedCount);
					testJobCountDetail.setSuccessCount(testJobCountDetail.getSuccessCount()+successCount);
					testJobCountDetail.setTotalCount(testJobCountDetail.getTotalCount()+totalCount);
					break;	
				}
			}
		}
		
			  
	}
	return testJobCountDetail;

	}
	
	private TestJobDetail findTestCaseCount(List<Build> buildList,  TestJobDetail testJobCountDetail) {
		for(Build build:buildList) {
		List<Action> actionList=build.getActions();
		if(!actionList.isEmpty()) {
			for(Action action:actionList) {
				if(Constants.TEST_REPORT_URL.equalsIgnoreCase(action.getUrlName())) {
					int failureCount=action.getFailCount();
					int skippedCount=action.getSkipCount();
					int totalCount=action.getTotalCount();
					int successCount=totalCount-(failureCount+skippedCount);
					
					testJobCountDetail.setFailureCount(testJobCountDetail.getFailureCount()+failureCount);
					testJobCountDetail.setSkippedCount(testJobCountDetail.getSkippedCount()+skippedCount);
					testJobCountDetail.setSuccessCount(testJobCountDetail.getSuccessCount()+successCount);
					testJobCountDetail.setTotalCount(testJobCountDetail.getTotalCount()+totalCount);
					break;	
				}
			}
		}
		
			  
	}
	return testJobCountDetail;

	}
	
	
	public List<TestJobDetail> acceptanceTestGroup() {
		return getTestCaseCountGroupByJob(Constants.ACCEPTANCE);
	}
	
	
	public List<TestJobDetail> getTestCaseCountGroupByJob(String jobName) {

		JenkinsDetails jenkinsDetails = jenkinswithTestReport();
		List<TestJobDetail> testJobList=new ArrayList<>(); 
		
		if (null != jenkinsDetails) {
			List<Job> jobList = jenkinsDetails.getJobs();

			if (!jobList.isEmpty()) {

				for (Job job : jobList) {
						if (!(jobName == null || job.getName().contains(jobName))) {
						continue;
						}
						TestJobDetail testJobCountDetail = new TestJobDetail();					
					List<Build> buildList = job.getBuilds();
					if (!buildList.isEmpty()) {
						findTestCaseCount(buildList,  testJobCountDetail);
					}
					testJobCountDetail.setName(job.getName());
					testJobList.add(testJobCountDetail);

					 
				}
			}

			

		}
		return testJobList;

	}
	
	
	public JenkinsCountDetail getAllBuildsCountbyDate(int limitMonthCount) {

		return getBuildsCountByDate(limitMonthCount, Constants.TYPE_ALL);
	}
	
	public JenkinsCountDetail getCITBuildsCountbyDate(int limitMonthCount) {

		return getBuildsCountByDate(limitMonthCount, Constants.UAT_DEPLOYMENT);
	}
	
	public JenkinsCountDetail getPRODBuildsCountbyDate(int limitMonthCount) {

		return getBuildsCountByDate(limitMonthCount, Constants.PROD_DEPLOYMENT);
	}
	
	private JenkinsCountDetail getBuildsCountByDate(int limitMonthCount, String jobType) {

		JenkinsDetails jenkinsDetails = getJenkinsDetails();
		Predicate<Build> timePredicate = build -> build.getTimestamp() >= calculateDateLimit(limitMonthCount);
		Predicate<Job> namePredicate = job -> jobType.equals(Constants.TYPE_ALL) ? Boolean.TRUE
				: job.getName().contains(jobType);

		long totalCount = 0;
		long successCount = 0;
		long failureCount = 0;

		Supplier<Stream<Build>> buildStream = () -> jenkinsDetails.getJobs().stream().filter(namePredicate)
				.flatMap(job -> job.getBuilds().stream()).filter(timePredicate);

		totalCount = buildStream.get().count();
		successCount = buildStream.get().filter(build -> Constants.SUCESS.equalsIgnoreCase(build.getResult())).count();
		failureCount = buildStream.get().filter(build -> Constants.FAILURE.equalsIgnoreCase(build.getResult())).count();

		JenkinsCountDetail jenkinsCountDetail = new JenkinsCountDetail();

		setCountOfBuilds(jenkinsCountDetail, Math.toIntExact(totalCount), Math.toIntExact(successCount),
				Math.toIntExact(failureCount));

		return jenkinsCountDetail;
	}
	
	private long calculateDateLimit(int limitMonthCount) {
		
		// LocalDate todaysDate = LocalDate.now(); Ideally this should be the code. Date
		// hard coded for testing purposes.
		LocalDate todaysDate = LocalDate.parse("2020-05-10");
		LocalDate limitDate = todaysDate.minusMonths(limitMonthCount);
		return limitDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
	
	private List<Deployment> lastSuccesfulRelease(String jobName) {

		JenkinsDetails jenkinsDetails = getSuccessfulBuildsDetails();
		Predicate<Job> namePredicate = job -> job.getName().contains(jobName);

		return jenkinsDetails
				.getJobs().stream().filter(
						namePredicate)
				.map(job -> new Deployment(job.getName(), job.getLastSuccessfulBuild().getTimestamp(),
						Instant.ofEpochMilli(job.getLastSuccessfulBuild().getTimestamp()).atZone(ZoneId.systemDefault())
								.toLocalDate()))
				.collect(Collectors.toList());
	}
	
	private JenkinsDetails getSuccessfulBuildsDetails() {
		
		String jenkinsUrl = this.url + Constants.SUCCESSFUL_BUILDS_API_URL; 
		return this.restTemplate.getForObject(jenkinsUrl, JenkinsDetails.class);
		
	}
}
