package com.deinersoft.checkwordster.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.SingleRootFileSource;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsLoader;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static us.monoid.web.Resty.content;

public class CucumberClientImplFake implements CucumberClientInterface {
    private static WireMockServer wireMockServer = null;

    public void startServer(){
        if (wireMockServer == null) {
            WireMockConfiguration wireMockConfiguration = new WireMockConfiguration();
            wireMockConfiguration.port(9010);
            wireMockConfiguration.browserProxyingEnabled();
            wireMockConfiguration.fileSource(new SingleRootFileSource("./wiremock"));

            wireMockServer = new WireMockServer(wireMockConfiguration);
            wireMockServer.loadMappingsUsing(new JsonFileMappingsLoader(new SingleRootFileSource("./wiremock/mappings")));
        }

        wireMockServer.start();
    };

    public void stopServer(){
        wireMockServer.stop();
    };

    public String getWords(String digits){
        String words = "";
        Resty resty  = new Resty();

        URL url = null;
        URI uri;

        try {
            url = new URL("http://localhost:9010/checkWordster");
            uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            String requestToPost = "{\"numberInDigits\": \"" + digits + "\"}";

            JSONResource response = resty.json(uri,content(new JSONObject(requestToPost)));
            words = (String) response.get("numberInWords");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return words;
    };
}
