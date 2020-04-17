package com.jenkins.constants;

public class Constants {

	public static final String JENKINS_DETAILS_API_URL = "/api/json?tree=jobs[name,url,builds[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]";
	public static final String SUCESS="Success";
	public static final String FAILURE="Failure";
	public static final String UAT_DEPLOYMENT = "UAT-deployment";
	public static final String PROD_DEPLOYMENT = "PROD-deployment";
	public static final String JENKINS_TESTDETAILS_API_URL = "/api/json?tree=jobs[name,url,builds[number,result,duration,url,actions[*]]]";
	public static final String TEST_REPORT_URL = "testReport";
	public static final String ACCEPTANCE = "Acceptance";
	public static final String TYPE_ALL = "ALL";
	public static final String SUCCESSFUL_BUILDS_API_URL = "/api/json?tree=jobs[name,url,lastSuccessfulBuild[number,result,duration,url,timestamp,actions[buildsByBranchName[*[*[*]]]]]]";


private Constants() {
	
}
	

}
