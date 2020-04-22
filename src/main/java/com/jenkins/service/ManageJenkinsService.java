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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jenkins.constants.Constants;
import com.jenkins.model.Action;
import com.jenkins.model.BuildDetail;
import com.jenkins.model.Deployment;
import com.jenkins.model.JenkinsCountDetail;
import com.jenkins.model.JenkinsDetail;
import com.jenkins.model.JenkinsDetails;
import com.jenkins.model.JenkinsJobCountDetail;
import com.jenkins.model.JobDetail;
import com.jenkins.model.TestJobCountDetail;
import com.jenkins.model.TestJobDetail;

import io.micrometer.core.instrument.util.StringUtils;

@Service
public class ManageJenkinsService {
  
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	
	 
	@Value("${jenkinsURL}")
	private String url;
	@Value("${usrName}")
	private  String usrname;
	@Value("${password}")
	private  String pswd;
	@Value("${dayLimit}")
	private Integer dayLimit;
	
	 
	
	
	
	

	private static final Logger LOGGER=LoggerFactory.getLogger(ManageJenkinsService.class);
	 
	
	
	private String jenkinsDetailsURL = "/api/xml?tree=jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]&xpath=/hudson/job/allBuild[timestamp[.>TIMESTAMP]]&wrapper=job";

	private String jenkinsDetailsURLBranch = "api/xml?tree=jobs[jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]]&xpath=/hudson/job/job/allBuild[timestamp[.>TIMESTAMP]]&wrapper=job";

	private String uatAPIURL = "/api/xml?tree=jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]&xpath=/hudson/job[name[contains(lower-case(.),'uat-deployment')]]/allBuild[timestamp[.>TIMESTAMP]]&wrapper=job";

	private String uatAPIURLBranch = "/api/xml?tree=jobs[jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]]&xpath=/hudson/job[job[name[contains(lower-case(.),'uat-deployment')]]/allBuild[timestamp[.>TIMESTAMP]]]&wrapper=job";

	private String prodAPIURL = "/api/xml?tree=jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]&xpath=/hudson/job[name[contains(lower-case(.),'prod-deployment')]]/allBuild[timestamp[.>TIMESTAMP]]&wrapper=job";

	private String prodAPIURLBranch = "/api/xml?tree=jobs[jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]]&xpath=/hudson/job[job[name[contains(lower-case(.),'prod-deployment')]]/allBuild[timestamp[.>TIMESTAMP]]]&wrapper=job";

	private String acceptanceTestURL = "/api/xml?tree=jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[*]]]&xpath=/hudson/job[name[contains(lower-case(.),'test-acceptance')]]/allBuild[timestamp[.>TIMESTAMP]]&wrapper=job"; 

	private String acceptanceTestURLBranch = "/api/xml?tree=jobs[jobs[name,url,allBuilds[number,result,duration,url,timestamp,actions[*]]]]&xpath=/hudson/job[job[name[contains(lower-case(.),'test-acceptance')]]/allBuild[timestamp[.>TIMESTAMP]]]&wrapper=job";

	private String uatLastSuccessURL = "/api/xml?tree=jobs[name,url,lastSuccessfulBuild[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]&xpath=/hudson/job[name[contains(lower-case(.),'uat-deployment')]]/lastSuccessfulBuild[timestamp[.>1]]&wrapper=job";

	private String uatLastSuccessURLBranch = "api/xml?tree=jobs[jobs[name,url,lastSuccessfulBuild[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]]&xpath=/hudson/job[job[name[contains(lower-case(.),'uat-deployment')]]/lastSuccessfulBuild[timestamp[.>1]]]&wrapper=job";

	private String prodLastSuccessURL = "/api/xml?tree=jobs[name,url,lastSuccessfulBuild[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]&xpath=/hudson/job[name[contains(lower-case(.),'prod-deployment')]]/lastSuccessfulBuild[timestamp[.>1]]&wrapper=job";

	private String prodLastSuccessURLBranch = "api/xml?tree=jobs[jobs[name,url,lastSuccessfulBuild[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]]&xpath=/hudson/job[job[name[contains(lower-case(.),'prod-deployment')]]/lastSuccessfulBuild[timestamp[.>1]]]&wrapper=job";

	public static final String TIMESTAMP = "TIMESTAMP";


	
	

	/**
	 * @param dayLimit
	 * @return JenkinsCountDetail total build count
	 * @ 
	 */
	public JenkinsCountDetail getTotalBuildCountJenkins(Integer dayLimit)  {

		JenkinsCountDetail jenkinsCountDetail = new JenkinsCountDetail();
		Map<String, JenkinsCountDetail> jenkinsJobMap = getCountGroupByJenkinsJob(null, getTimeStampPast(dayLimit));
		jenkinsJobMap.forEach((jobName, jenkinsCount) -> {
			jenkinsCountDetail.setFailureCount(jenkinsCountDetail.getFailureCount() + jenkinsCount.getFailureCount());
			jenkinsCountDetail.setSuccessCount(jenkinsCountDetail.getSuccessCount() + jenkinsCount.getSuccessCount());
			jenkinsCountDetail.setTotalCount(jenkinsCountDetail.getTotalCount() + jenkinsCount.getTotalCount());

		});
		return jenkinsCountDetail;
	}

	/**
	 * @param dayLimit
	 * @return long get timestamp based on day count
	 */
	private long getTimeStampPast(Integer dayLimit) {
		if (null == dayLimit) {
			dayLimit = this.dayLimit;
		}

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -dayLimit);
		return cal.getTimeInMillis();
	}

	/**
	 * @param dayLimit
	 * @return JenkinsCountDetail get UAT build count
	 * @ 
	 */
	public JenkinsCountDetail getTotalBuildCountUAT(Integer dayLimit)  {
		JenkinsCountDetail jenkinsCountDetail = new JenkinsCountDetail();
		Map<String, JenkinsCountDetail> jenkinsJobMap = getCountGroupByJenkinsJob(Constants.UAT_DEPLOYMENT,
				getTimeStampPast(dayLimit));
		jenkinsJobMap.forEach((jobName, jenkinsCount) -> {
			jenkinsCountDetail.setFailureCount(jenkinsCountDetail.getFailureCount() + jenkinsCount.getFailureCount());
			jenkinsCountDetail.setSuccessCount(jenkinsCountDetail.getSuccessCount() + jenkinsCount.getSuccessCount());
			jenkinsCountDetail.setTotalCount(jenkinsCountDetail.getTotalCount() + jenkinsCount.getTotalCount());

		});
		return jenkinsCountDetail;
	}

	/**
	 * @param dayLimit
	 * @return JenkinsCountDetail get PROD build count
	 * @ 
	 */
	public JenkinsCountDetail getTotalBuildCountPROD(Integer dayLimit)  {
		JenkinsCountDetail jenkinsCountDetail = new JenkinsCountDetail();
		Map<String, JenkinsCountDetail> jenkinsJobMap = getCountGroupByJenkinsJob(Constants.PROD_DEPLOYMENT,
				getTimeStampPast(dayLimit));
		jenkinsJobMap.forEach((jobName, jenkinsCount) -> {
			jenkinsCountDetail.setFailureCount(jenkinsCountDetail.getFailureCount() + jenkinsCount.getFailureCount());
			jenkinsCountDetail.setSuccessCount(jenkinsCountDetail.getSuccessCount() + jenkinsCount.getSuccessCount());
			jenkinsCountDetail.setTotalCount(jenkinsCountDetail.getTotalCount() + jenkinsCount.getTotalCount());

		});
		return jenkinsCountDetail;
	}

	/**
	 * @param dayLimit
	 * @return TestJobCountDetail get Acceptance job test case count
	 * @ 
	 */
	public TestJobCountDetail getTotalCountAcceptance(Integer dayLimit)  {

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

	/**
	 * @param dayLimit
	 * @return Map<String, JenkinsCountDetail> count grouped by job
	 * @ 
	 */
	public List<JenkinsJobCountDetail> getCountGroupByUATDeploymnt(Integer dayLimit)  {
		
		List<JenkinsJobCountDetail> list=new ArrayList<>();
		Map<String, JenkinsCountDetail> jenkinsJobMap = getCountGroupByJenkinsJob(Constants.UAT_DEPLOYMENT, getTimeStampPast(dayLimit));
		convertMaptoList(list, jenkinsJobMap);
		return list;

	}

	/**
	 * @param List<JenkinsJobCountDetail>
	 * @param Map<String, JenkinsCountDetail>
	 */
	private void convertMaptoList(List<JenkinsJobCountDetail> list, Map<String, JenkinsCountDetail> jenkinsJobMap) {
		jenkinsJobMap.forEach((jobName, jenkinsCount) -> {
			JenkinsJobCountDetail jenkinsCountDetail = new JenkinsJobCountDetail();

			jenkinsCountDetail.setFailureCount(jenkinsCountDetail.getFailureCount() + jenkinsCount.getFailureCount());
			jenkinsCountDetail.setSuccessCount(jenkinsCountDetail.getSuccessCount() + jenkinsCount.getSuccessCount());
			jenkinsCountDetail.setTotalCount(jenkinsCountDetail.getTotalCount() + jenkinsCount.getTotalCount());
			jenkinsCountDetail.setJobname(jobName);
			list.add(jenkinsCountDetail);

		});
	}

	/**
	 * @param dayLimit
	 * @return Map<String, JenkinsCountDetail> count grouped by job
	 * @ 
	 */
	public List<JenkinsJobCountDetail> getCountGroupByPRODDeploymnt(Integer dayLimit)  {
		
		List<JenkinsJobCountDetail> list=new ArrayList<>();
		Map<String, JenkinsCountDetail> jenkinsJobMap = getCountGroupByJenkinsJob(Constants.PROD_DEPLOYMENT, getTimeStampPast(dayLimit));
		convertMaptoList(list, jenkinsJobMap);
		return list;


	}

	/**
	 * @param dayLimit
	 * @return Map<String, TestJobCountDetail> count grouped by job
	 * @ 
	 */
	public List<TestJobDetail> getCountGroupByAcceptancTest(Integer dayLimit)  {
		
		
		List<TestJobDetail> list=new ArrayList<>();
		Map<String, TestJobCountDetail> jenkinsJobMap = getCountGroupByAcceptance(getTimeStampPast(dayLimit));
		jenkinsJobMap.forEach((jobName, jenkinsCount) -> {
			TestJobDetail jenkinsCountDetail = new TestJobDetail();
			jenkinsCountDetail.setFailureCount(jenkinsCountDetail.getFailureCount() + jenkinsCount.getFailureCount());
			jenkinsCountDetail.setSuccessCount(jenkinsCountDetail.getSuccessCount() + jenkinsCount.getSuccessCount());
			jenkinsCountDetail.setTotalCount(jenkinsCountDetail.getTotalCount() + jenkinsCount.getTotalCount());
			jenkinsCountDetail.setSkippedCount(jenkinsCountDetail.getSkippedCount() + jenkinsCount.getSkippedCount());
			jenkinsCountDetail.setName(jobName);
			list.add(jenkinsCountDetail);
		});
		return list;
		

	}

	/**
	 * @param deployment
	 * @param dayLimit
	 * @return List<JenkinsJobCountDetail>
	 * @ 
	 */
	public List<JenkinsJobCountDetail> getCountGroupByJenkinsJob(Integer dayLimit)  {

		
		List<JenkinsJobCountDetail> list=new ArrayList<>();
		Map<String, JenkinsCountDetail> jenkinsJobMap = getCountGroupByJenkinsJob(null, getTimeStampPast(dayLimit)); 
		convertMaptoList(list, jenkinsJobMap);
		return list;
	}

	/**
	 * @param deployment
	 * @param dayLimit
	 * @return Map<String, JenkinsCountDetail> get count based on type UAT/PROD/All
	 * @ 
	 * @ 
	 */
	public Map<String, JenkinsCountDetail> getCountGroupByJenkinsJob(String deployment, long dayLimit)   {
		
			String timestamp = String.valueOf(dayLimit);
			String urlString = "";
			String urlStringBranch = "";

			if (Constants.UAT_DEPLOYMENT.equalsIgnoreCase(deployment)) {
				urlString = uatAPIURL;
				urlStringBranch = uatAPIURLBranch;
			} else if (Constants.PROD_DEPLOYMENT.equalsIgnoreCase(deployment)) {
				urlString = prodAPIURL;
				urlStringBranch = prodAPIURLBranch; 

			} else {
				urlString = jenkinsDetailsURL;
				urlStringBranch = jenkinsDetailsURLBranch;

			}
			JenkinsDetail jobBranch = new JenkinsDetail();
			JenkinsDetails jobBranchDeploy = new JenkinsDetails();
			JenkinsDetail job = getJenkinsJobDetails(timestamp, urlString);
			if (!StringUtils.isBlank(urlStringBranch) && jenkinsDetailsURLBranch.equalsIgnoreCase(urlStringBranch)) {
				jobBranch = getJenkinsJobDetails(timestamp, urlStringBranch);
				setAdditionalCountByGroup(jobBranch, job);
			} else if (!StringUtils.isBlank(urlStringBranch) && (uatAPIURLBranch.equalsIgnoreCase(urlStringBranch)
					|| prodAPIURLBranch.equalsIgnoreCase(urlStringBranch))) {
				jobBranchDeploy = getJenkinsJobDetailsBranch(timestamp, urlStringBranch);
				setAdditionalJobs(jobBranchDeploy, job);

			}

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

		

	}

	public void setAdditionalCountByGroup(JenkinsDetail jobBranch, JenkinsDetail job) {
		List<BuildDetail> buildList = new ArrayList<>();

		if (null != job && null != job.getJob() && null != job.getJob().getAllBuild() && null != jobBranch
				&& null != jobBranch.getJob() && null != jobBranch.getJob().getAllBuild()) {
			buildList = jobBranch.getJob().getAllBuild();
			List<BuildDetail> buildListOld = new ArrayList<>();
			buildListOld = job.getJob().getAllBuild();
			buildListOld.addAll(buildList);
			job.getJob().setAllBuild(buildListOld);
		}
		if( (null!=job) &&  null==job.getJob() && null != jobBranch
				&& null != jobBranch.getJob() && null != jobBranch.getJob().getAllBuild()) {
			buildList = jobBranch.getJob().getAllBuild();
			JobDetail jobDtl=new JobDetail();
			jobDtl.setAllBuild(buildList);
			job.setJob(jobDtl);
			
		}
	}

	/**
	 * @param JenkinsDetails
	 * @param JenkinsDetail
	 * setting additional job with in the jobs
	 */
	public void setAdditionalJobs(JenkinsDetails jobBranchDeploy, JenkinsDetail job) {
		List<BuildDetail> buildList = new ArrayList<>();
		if (null != job && null != job.getJob() && null != job.getJob().getAllBuild() && null != jobBranchDeploy
				&& null != jobBranchDeploy.getJob() && null != jobBranchDeploy.getJob().getJob()
				&& null != jobBranchDeploy.getJob().getJob().getJob()) {


			List<BuildDetail> buildListOld = new ArrayList<>();
			buildListOld = job.getJob().getAllBuild();
			setAllBuildList(jobBranchDeploy, buildList);
			buildListOld.addAll(buildList);
			job.getJob().setAllBuild(buildListOld);

		}
		if( (null!=job) &&  null==job.getJob() && null!=jobBranchDeploy && null !=jobBranchDeploy.getJob() && null !=jobBranchDeploy.getJob().getJob().getJob()) {
			
			JobDetail detail=new JobDetail();
			setAllBuildList(jobBranchDeploy, buildList);
			detail.setAllBuild(buildList);
			job.setJob(detail);
			
			
		}
	}

	private void setAllBuildList(JenkinsDetails jobBranchDeploy, List<BuildDetail> buildList) {
		for (JobDetail jobVal : jobBranchDeploy.getJob().getJob().getJob()) {
			if (null != jobVal && null != jobVal.getAllBuild()) {

				buildList.addAll(jobVal.getAllBuild());
			}
		}
	}

	/**
	 * @param timestamp
	 * @param urlString
	 * @return JenkinsDetail
	 * @
	 * get details invoking jenkins API
	 */
	private JenkinsDetail getJenkinsJobDetails(String timestamp, String urlString) {
		urlString = urlString.replace(TIMESTAMP, timestamp);

		
        LOGGER.info("get jenkins Job details");

		String json = getJenkinsResponseXml(urlString);
		// Object mapper instance
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		// Convert JSON to POJO
		try {
			return mapper.readValue(json, JenkinsDetail.class);
		} catch (JsonProcessingException e) {
			LOGGER.error("error whil parsing JSON ",e);
		}
		return null;
	}

	/**
	 * @param timestamp
	 * @param urlString
	 * @return JenkinsDetails
	 * @
	 * get details of jobs with in jobs invoking jenkins API 
	 */
	private JenkinsDetails getJenkinsJobDetailsBranch(String timestamp, String urlString)
			 {
        LOGGER.info("get jenkins Job details with in job");

		urlString = urlString.replace(TIMESTAMP, timestamp);

		String json = getJenkinsResponseXml(urlString);
		// Object mapper instance
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

		// Convert JSON to POJO
		try {
			return mapper.readValue(json, JenkinsDetails.class);
		} catch (JsonProcessingException e) {

			LOGGER.error("error whil parsing JSON ",e);
		}
		return null;
		
	}

	/**
	 * @param timeStamp
	 * @return Map<String, TestJobCountDetail> count grouped by acceptance job
	 * @ 
	 * @ 
	 */
	public Map<String, TestJobCountDetail> getCountGroupByAcceptance(long timeStamp)    {
		
			String timestamp = String.valueOf(timeStamp);

			JenkinsDetail job = getJenkinsJobDetails(timestamp, acceptanceTestURL);

			JenkinsDetails jobBranchDeploy = getJenkinsJobDetailsBranch(timestamp, acceptanceTestURLBranch);

			setAdditionalJobs(jobBranchDeploy, job);

			Map<String, List<BuildDetail>> buildDetailMap = buildMap(job);
			Map<String, TestJobCountDetail> jenkinsJobMap = new LinkedHashMap<>();
			buildDetailMap.forEach((jobName, buildDetailList) -> {

				TestJobCountDetail testJobCountDetail = new TestJobCountDetail();
				findTestCaseCountBuildDetail(buildDetailList, testJobCountDetail);
				jenkinsJobMap.put(jobName, testJobCountDetail);
			});
			return jenkinsJobMap;

		
	}

	public List<Deployment> lastUATDeployments()  {
		return lastDeployments(uatLastSuccessURL, uatLastSuccessURLBranch);
	}

	public List<Deployment> lastProdDeployments()  {
		return lastDeployments(prodLastSuccessURL, prodLastSuccessURLBranch);

	}

	/**
	 * @param url
	 * @return List<Deployment> last deployment data
	 * @ 
	 * @ 
	 */
	public List<Deployment> lastDeployments(String url, String urlBranch)   {
		List<Deployment> deployList = new ArrayList<>();

			JenkinsDetail job = getJenkinsJobDetails("", url);

			JenkinsDetails jobBranchDeploy = getJenkinsJobDetailsBranch("", urlBranch);

			setAdditionalCase(job, jobBranchDeploy);

			if (null != job && null != job.getJob()) {
				List<BuildDetail> lastBuilds = job.getJob().getLastSuccessfulBuild();

				setDeploymentValues(lastBuilds, deployList);
			}
			return deployList;
		

	}

	public void setAdditionalCase(JenkinsDetail job, JenkinsDetails jobBranchDeploy) {

		List<BuildDetail> buildList = new ArrayList<>();
		if (null != job && null != job.getJob() && null != job.getJob().getLastSuccessfulBuild()
				&& null != jobBranchDeploy && null != jobBranchDeploy.getJob()
				&& null != jobBranchDeploy.getJob().getJob() && null != jobBranchDeploy.getJob().getJob().getJob()) {

			List<BuildDetail> buildListOld = new ArrayList<>();
			buildListOld = job.getJob().getLastSuccessfulBuild();
			setBuildList(jobBranchDeploy, buildList);
			buildListOld.addAll(buildList);
			job.getJob().setLastSuccessfulBuild(buildListOld);

		}
		if ((null != job) && null == job.getJob() && null != jobBranchDeploy
				&& null != jobBranchDeploy.getJob().getJob().getJob()) {

			JobDetail detail = new JobDetail();
			setBuildList(jobBranchDeploy, buildList);
			detail.setLastSuccessfulBuild(buildList);
			job.setJob(detail);

		}
	}

	private void setBuildList(JenkinsDetails jobBranchDeploy, List<BuildDetail> buildList) {
		for (JobDetail jobVal : jobBranchDeploy.getJob().getJob().getJob()) {
			if (null != jobVal && null != jobVal.getLastSuccessfulBuild()) {

				buildList.addAll(jobVal.getLastSuccessfulBuild());
			}
		}
	}

	/**
	 * @param lastBuilds
	 * @param deployList set DeploymentValues
	 */
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

	/**
	 * @param url
	 * @return String get the result from jenkins API
	 */
	private String getJenkinsResponseXml(String url) {

         LOGGER.info("invoking Jenkins API");
		RestTemplate restTemplate = this.restTemplateBuilder.basicAuthentication(usrname,pswd).build();	
		
		String jenkinsUrl = this.url + url;
		String xml = restTemplate.getForObject(jenkinsUrl, String.class);

		JSONObject jsonObj = XML.toJSONObject(xml);
		return jsonObj.toString();
	}
	
	
	

	/**
	 * @param job
	 * @return Map<String,List<BuildDetail>> creat a build Map grouped by job
	 */
	private Map<String, List<BuildDetail>> buildMap(JenkinsDetail job) {
		Map<String, List<BuildDetail>> buildMap = new LinkedHashMap<>();

		if(null!=job && null!=job.getJob()) {
		List<BuildDetail> buildDetailsList = job.getJob().getAllBuild();
		for (BuildDetail build : buildDetailsList) {
			if (null != buildMap.get(build.getJobName())) {
				List<BuildDetail> buildList = buildMap.get(build.getJobName());
				buildList.add(build);
				buildMap.put(build.getJobName(), buildList);
			} else {
				List<BuildDetail> buildList = new ArrayList<>();
				buildList.add(build);
				buildMap.put(build.getJobName(), buildList);
			}
		}
		return buildMap;
		}
		return buildMap;

	}

	/**
	 * @param jenkinsCountDetail
	 * @param totalCount
	 * @param successCount
	 * @param failureCount       set CountOfBuilds
	 */
	private void setCountOfBuilds(JenkinsCountDetail jenkinsCountDetail, Integer totalCount, Integer successCount,
			Integer failureCount) {
		jenkinsCountDetail.setFailureCount(failureCount);
		jenkinsCountDetail.setSuccessCount(successCount);
		jenkinsCountDetail.setTotalCount(totalCount);
	}

	/**
	 * @param buildList
	 * @param successCount
	 * @return Integer find successful build
	 */
	private Integer findBuildDetailCount(List<BuildDetail> buildList, Integer successCount) {
		for (BuildDetail build : buildList) {

			if (Constants.SUCESS.equalsIgnoreCase(build.getResult())) {
				successCount = successCount + 1;
			}

		}
		return successCount;
	}

	/**
	 * @param buildList
	 * @param testJobCountDetail
	 * @return TestJobCountDetail find test case count detail and group by
	 *         total/failure/success
	 */
	private TestJobCountDetail findTestCaseCountBuildDetail(List<BuildDetail> buildList,
			TestJobCountDetail testJobCountDetail) {
		for (BuildDetail build : buildList) {
			List<Action> actionList = build.getAction();
			if (!actionList.isEmpty()) {
				for (Action action : actionList) {
					if (null != action && Constants.TEST_REPORT_URL.equalsIgnoreCase(action.getUrlName())) {
						int failureCount = action.getFailCount();
						int skippedCount = action.getSkipCount();
						int totalCount = action.getTotalCount();
						int successCount = totalCount - (failureCount + skippedCount);

						testJobCountDetail.setFailureCount(testJobCountDetail.getFailureCount() + failureCount);
						testJobCountDetail.setSkippedCount(testJobCountDetail.getSkippedCount() + skippedCount);
						testJobCountDetail.setSuccessCount(testJobCountDetail.getSuccessCount() + successCount);
						testJobCountDetail.setTotalCount(testJobCountDetail.getTotalCount() + totalCount);
						break;
					}
				}
			}

		}
		return testJobCountDetail;

	}

}
 