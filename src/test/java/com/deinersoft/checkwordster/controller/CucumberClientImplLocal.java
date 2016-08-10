package com.deinersoft.checkwordster.controller;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CucumberClientImplLocal implements CucumberClientInterface {

    public void startServer(){
    };

    public void stopServer(){
    };

    public String getWords(String digits){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject request = new JSONObject();
        request.put("stringOfDigits", digits);

        HttpEntity<String> httpRequest = new HttpEntity<String>(request.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9000/convertToWords", httpRequest, String.class);
        JSONObject json = new JSONObject(response.getBody());
        return json.getString("numberInWords");

    };
}

