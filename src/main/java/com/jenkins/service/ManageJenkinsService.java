package com.jenkins.service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jenkins.constants.Constants;
import com.jenkins.model.Action;
import com.jenkins.model.BuildDetail;
import com.jenkins.model.Deployment;
import com.jenkins.model.JenkinsDetail;  


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
	
	private String jenkinsDetailsURL = "/api/xml?tree=jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]&xpath=/hudson/job/allBuild[timestamp[.>TIMESTAMP]]&wrapper=job";

	private String uatAPIURL = "/api/xml?tree=jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]&xpath=/hudson/job[name[contains(lower-case(.),'uat-deployment')]]/allBuild[timestamp[.>TIMESTAMP]]&wrapper=job";

	
	private String prodAPIURL = "/api/xml?tree=jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]&xpath=/hudson/job[name[contains(lower-case(.),'prod-deployment')]]/allBuild[timestamp[.>TIMESTAMP]]&wrapper=job";

	private String acceptanceTestURL = "/api/xml?tree=jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[*]]]&xpath=/hudson/job[name[contains(lower-case(.),'acceptance')]]/allBuild[timestamp[.>TIMESTAMP]]&wrapper=job";

	
	private String uatLastSuccessURL = "/api/xml?tree=jobs[name,url,lastSuccessfulBuild[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]&xpath=/hudson/job[name[contains(lower-case(.),'uat-deployment')]]/lastSuccessfulBuild[timestamp[.>1]]&wrapper=job";

	private String prodLastSuccessURL = "/api/xml?tree=jobs[name,url,lastSuccessfulBuild[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]&xpath=/hudson/job[name[contains(lower-case(.),'prod-deployment')]]/lastSuccessfulBuild[timestamp[.>1]]&wrapper=job";

	
	public static final String TIMESTAMP = "TIMESTAMP";
 
	
	public ManageJenkinsService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder
				.basicAuthentication(usrname, pswd)
				.build();
	}
	
	
	public JenkinsCountDetail getTotalBuildCountJenkins(Integer dayLimit) {
		
		
		
		JenkinsCountDetail jenkinsCountDetail = new JenkinsCountDetail();
		Map<String, JenkinsCountDetail> jenkinsJobMap = getCountGroupByJenkinsJob(null,getTimeStampPast(dayLimit));
		jenkinsJobMap.forEach((jobName, jenkinsCount) -> {
			jenkinsCountDetail.setFailureCount(jenkinsCountDetail.getFailureCount() + jenkinsCount.getFailureCount());
			jenkinsCountDetail.setSuccessCount(jenkinsCountDetail.getSuccessCount() + jenkinsCount.getSuccessCount());
			jenkinsCountDetail.setTotalCount(jenkinsCountDetail.getTotalCount() + jenkinsCount.getTotalCount());

		});
		return jenkinsCountDetail;
	}



	private long getTimeStampPast(Integer dayLimit) {
		if(null==dayLimit) {
			dayLimit=30;
		}
			
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -dayLimit);
		return cal.getTimeInMillis();
	}
	
	
	public JenkinsCountDetail getTotalBuildCountUAT(Integer dayLimit) {
		JenkinsCountDetail jenkinsCountDetail = new JenkinsCountDetail();
		Map<String, JenkinsCountDetail> jenkinsJobMap = getCountGroupByJenkinsJob(Constants.UAT_DEPLOYMENT,getTimeStampPast(dayLimit));
		jenkinsJobMap.forEach((jobName, jenkinsCount) -> {
			jenkinsCountDetail.setFailureCount(jenkinsCountDetail.getFailureCount() + jenkinsCount.getFailureCount());
			jenkinsCountDetail.setSuccessCount(jenkinsCountDetail.getSuccessCount() + jenkinsCount.getSuccessCount());
			jenkinsCountDetail.setTotalCount(jenkinsCountDetail.getTotalCount() + jenkinsCount.getTotalCount());

		});
		return jenkinsCountDetail;
	}
	
	
	public JenkinsCountDetail getTotalBuildCountPROD(Integer dayLimit) {
		JenkinsCountDetail jenkinsCountDetail = new JenkinsCountDetail();
		Map<String, JenkinsCountDetail> jenkinsJobMap = getCountGroupByJenkinsJob(Constants.PROD_DEPLOYMENT,getTimeStampPast(dayLimit));
		jenkinsJobMap.forEach((jobName, jenkinsCount) -> {
			jenkinsCountDetail.setFailureCount(jenkinsCountDetail.getFailureCount() + jenkinsCount.getFailureCount());
			jenkinsCountDetail.setSuccessCount(jenkinsCountDetail.getSuccessCount() + jenkinsCount.getSuccessCount());
			jenkinsCountDetail.setTotalCount(jenkinsCountDetail.getTotalCount() + jenkinsCount.getTotalCount());

		});
		return jenkinsCountDetail;
	}	
	
	public TestJobCountDetail getTotalCountAcceptance(Integer dayLimit) {
		
		TestJobCountDetail jenkinsCountDetail = new TestJobCountDetail();
	    Map<String, TestJobCountDetail> jenkinsJobMap = getCountGroupByAcceptance(getTimeStampPast(dayLimit));
		jenkinsJobMap.forEach((jobName, jenkinsCount) -> {
			jenkinsCountDetail.setFailureCount(jenkinsCountDetail.getFailureCount() + jenkinsCount.getFailureCount());
			jenkinsCountDetail.setSuccessCount(jenkinsCountDetail.getSuccessCount() + jenkinsCount.getSuccessCount());
			jenkinsCountDetail.setTotalCount(jenkinsCountDetail.getTotalCount() + jenkinsCount.getTotalCount());
			jenkinsCountDetail.setSkippedCount(jenkinsCountDetail.getSkippedCount() + jenkinsCount.getSkippedCount());

		});
		return jenkinsCountDetail;
	}	
	
		public Map<String, JenkinsCountDetail> getCountGroupByUATDeploymnt(Integer dayLimit) {
		 return getCountGroupByJenkinsJob(Constants.UAT_DEPLOYMENT,getTimeStampPast(dayLimit));
		 
		}
		
		public Map<String, JenkinsCountDetail> getCountGroupByPRODDeploymnt(Integer dayLimit) {
			 return getCountGroupByJenkinsJob(Constants.PROD_DEPLOYMENT,getTimeStampPast(dayLimit));
	
		}
		public Map<String, TestJobCountDetail> getCountGroupByAcceptancTest(Integer dayLimit) {
			 return getCountGroupByAcceptance(getTimeStampPast(dayLimit));
	
		}
		
		public Map<String, JenkinsCountDetail> getCountGroupByJenkinsJob(String deployment,Integer dayLimit) {
			
			return getCountGroupByJenkinsJob(deployment,getTimeStampPast(dayLimit));
		}
	
	public Map<String, JenkinsCountDetail> getCountGroupByJenkinsJob(String deployment,long dayLimit) {
		try {
			String timestamp = String.valueOf(dayLimit);
			String urlString = "";
			
			
			if(Constants.UAT_DEPLOYMENT.equalsIgnoreCase(deployment)) {
				urlString=uatAPIURL;	
			}
			else if(Constants.PROD_DEPLOYMENT.equalsIgnoreCase(deployment)) {
				urlString=prodAPIURL;	
			}else {
				urlString=jenkinsDetailsURL;

			}
			
			urlString=urlString.replace(TIMESTAMP, timestamp);	

			String json = getJenkinsResponseXml(urlString);
			// Object mapper instance
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

			// Convert JSON to POJO
			JenkinsDetail job = mapper.readValue(json, JenkinsDetail.class);

			Map<String, List<BuildDetail>> buildMap = buildMap(job);
			Map<String, JenkinsCountDetail> jenkinsJobMap = new LinkedHashMap<>();
		
			buildMap.forEach((jobName, buildList) -> {


				int successCount = 0;
				successCount = findBuildDetailCount(buildList, successCount);
				int totalCount = buildList.size();
				int failureCount = totalCount - successCount;
				JenkinsCountDetail jenkinsCountDetail = new JenkinsCountDetail();
				setCountOfBuilds(jenkinsCountDetail, totalCount, successCount, failureCount);
				jenkinsJobMap.put(jobName, jenkinsCountDetail);

			});

			return jenkinsJobMap;

		} catch (Exception e) {

			return null;
		}

	}

	
	
	public Map<String, TestJobCountDetail> getCountGroupByAcceptance(long timeStamp) {
		try {
			String timestamp = String.valueOf(timeStamp);
			String urlString = "";
			urlString=acceptanceTestURL.replace(TIMESTAMP, timestamp);	
			String json = getJenkinsResponseXml(urlString);
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			JenkinsDetail job = mapper.readValue(json, JenkinsDetail.class);
			Map<String, List<BuildDetail>> buildDetailMap = buildMap(job);
			Map<String, TestJobCountDetail> jenkinsJobMap = new LinkedHashMap<>();
			buildDetailMap.forEach((jobName, buildDetailList) -> {

				TestJobCountDetail testJobCountDetail=new TestJobCountDetail();
				findTestCaseCountBuildDetail(buildDetailList, testJobCountDetail);
				jenkinsJobMap.put(jobName,testJobCountDetail);
			});
			return jenkinsJobMap; 

		} catch (Exception e) {

			return null;
		}

	}
	
	
	public List<Deployment> lastUATDeployments() {
		return lastDeployments(uatLastSuccessURL);
	}
	
	public List<Deployment> lastProdDeployments() {
		return lastDeployments(prodLastSuccessURL);

	}
	
	public List<Deployment> lastDeployments(String url) {
		List<Deployment> deployList = new ArrayList<>();
		try {
			String json = getJenkinsResponseXml(url);
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			JenkinsDetail job = mapper.readValue(json, JenkinsDetail.class);
			List<BuildDetail> lastBuilds = job.getJob().getLastSuccessfulBuild();
			
			setDeploymentValues(lastBuilds, deployList);
			return deployList;
		} catch (Exception e) {
			return deployList;
		}

	}



	private void setDeploymentValues(List<BuildDetail> lastBuilds, List<Deployment> deployList) {
		lastBuilds.forEach(build -> {
			Deployment deploy = new Deployment();
			deploy.setJobName(build.getJobName());
			deploy.setLastSuccessfulRun(build.getTimestamp());
			deploy.setSuccessfulRunDate(
					Instant.ofEpochMilli(build.getTimestamp()).atZone(ZoneId.systemDefault()).toLocalDate());
			deploy.setBuildNo(build.getNumber());
			deployList.add(deploy);
		});
	}

	private String getJenkinsResponseXml(String url) {

		String jenkinsUrl = this.url + url;
		String xml = this.restTemplate.getForObject(jenkinsUrl, String.class);

		JSONObject jsonObj = XML.toJSONObject(xml);
		return jsonObj.toString();
	}



	private Map<String,List<BuildDetail>> buildMap(JenkinsDetail job) {
		List<BuildDetail> buildDetailsList=job.getJob().getAllBuild();
        Map<String,List<BuildDetail>> buildMap=new LinkedHashMap<>();
        for(BuildDetail build:buildDetailsList) {
        	if(null!=buildMap.get(build.getJobName())) {
        		List<BuildDetail> buildList=buildMap.get(build.getJobName());
        		buildList.add(build);
        		buildMap.put(build.getJobName(), buildList);
        	}else{
        		List<BuildDetail> buildList=new ArrayList<>();
        		buildList.add(build);
        		buildMap.put(build.getJobName(), buildList);	
        	}
        }
        return buildMap;
        
	}
	
	
	


	
	
	
	

	
	

	private void setCountOfBuilds(JenkinsCountDetail jenkinsCountDetail, Integer totalCount, Integer successCount,
			Integer failureCount) {
		jenkinsCountDetail.setFailureCount(failureCount);
		jenkinsCountDetail.setSuccessCount(successCount);
		jenkinsCountDetail.setTotalCount(totalCount);
	}
	
	

	

	
	
	


	private Integer findBuildDetailCount(List<BuildDetail> buildList,  Integer successCount) {
		for(BuildDetail build:buildList) {
			
			  if(Constants.SUCESS.equalsIgnoreCase(build.getResult())) {
				  successCount=successCount+1; 
			  }
			   
		  }
		return successCount;
	}
	
	

	
	
	
	
	
	

	
	
		
		private TestJobCountDetail findTestCaseCountBuildDetail(List<BuildDetail> buildList,  TestJobCountDetail testJobCountDetail) {
			for(BuildDetail build:buildList) {
			List<Action> actionList=build.getAction();
			if(!actionList.isEmpty()) {
				for(Action action:actionList) {
					if(null!=action && Constants.TEST_REPORT_URL.equalsIgnoreCase(action.getUrlName())) {
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
	

	
	
	
	
	
	
	
	
	

	

	
	
	

}
