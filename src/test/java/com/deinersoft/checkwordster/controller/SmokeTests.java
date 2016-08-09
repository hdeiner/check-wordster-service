package com.deinersoft.checkwordster.controller;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SmokeTests {
	ResponseEntity<String> response;

	@Before
	public void runTheSmokeTest() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject request = new JSONObject();
		request.put("stringOfDigits", "19540520");

		HttpEntity<String> httpRequest = new HttpEntity<String>(request.toString(), headers);
		response = restTemplate.postForEntity("http://localhost:9000/convertToWords", httpRequest, String.class);
	}

	@Test
	public void checkSmokeTestStatus() throws Exception {
		assertThat(response.getStatusCode(), is(HttpStatus.OK));

		JSONObject json = new JSONObject(response.getBody());
		assertThat(json.getString("numberInWords"), is("Nineteen million five hundred fourty thousand five hundred twenty"));

	}

	@Test
	public void checkSmokeTestResponse() throws Exception {
		JSONObject json = new JSONObject(response.getBody());
		assertThat(json.getString("numberInWords"), is("Nineteen million five hundred fourty thousand five hundred twenty"));

	}
}
