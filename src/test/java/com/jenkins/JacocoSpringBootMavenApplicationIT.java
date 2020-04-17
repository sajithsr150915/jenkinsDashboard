package com.jenkins;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

class JacocoSpringBootMavenApplicationIT {

	
	
	
	
	@Test
	public void jenkinsBuildDetails() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = Constants.BASE_URL + "manageJenkins/jenkinsBuildDetails";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, result.getStatusCodeValue());
	}
	
	
	
	
	
	

}

