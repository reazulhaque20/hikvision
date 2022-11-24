package com.apex.hikvision.controller;

import com.apex.hikvision.responseMapperModel.Root;
import com.burgstaller.okhttp.AuthenticationCacheInterceptor;
import com.burgstaller.okhttp.CachingAuthenticatorDecorator;
import com.burgstaller.okhttp.digest.CachingAuthenticator;
import com.burgstaller.okhttp.digest.DigestAuthenticator;
import com.burgstaller.okhttp.digest.Credentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("check/hikvision")
public class TestController {

    RestTemplate restTemplate;
    @GetMapping("/connect")
    String hikvisionConnect() {
        String userName = "admin";
        String password = "Hik12345";
        String body = "{\n" +
                "   \"AcsEventCond\":{\n" +
                "      \"searchID\":\"3242s34\",\n" +
                "      \"searchResultPosition\":0,\n" +
                "      \"numOfMatches\": 300,\n" +
                "      \"maxResults\":600,\n" +
                "      \"major\":0,\n" +
                "      \"minor\":0,\n" +
                "      \"startTime\":\"2022-09-09T17:30:08+08:00\",\n" +
                "      \"endTime\":\"2022-12-12T17:30:08+08:00\"\n" +
                "   }\n" +
                "}";

        Response response = testFunction();
        System.out.println("Response: " + response);
        String result = "";
        try{
            result = response.body().string();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject(result);
        ObjectMapper objectMapper = new ObjectMapper();
        Root root = new Root();
        try{
            root = objectMapper.readValue(jsonObject.toString(), Root.class);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.println("Root: " + root);
        return "Connected";
    }

    public Response testFunction(){
        String userName = "admin";
        String password = "Hik12345";
        String start = "0";
        String length = "600";
        Response responses = null;
        DigestAuthenticator authenticator = new DigestAuthenticator(new Credentials(userName, password));
        Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();
        OkHttpClient client = new OkHttpClient.Builder()
                .authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
                .addInterceptor(new AuthenticationCacheInterceptor(authCache))
                .build();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "{\"UserInfoSearchCond\": { \"searchID\": \"123213\",\"searchResultPosition\": "+start+",\"maxResults\": "+length+"}}");
        String url = "http://192.168.4.24/ISAPI/AccessControl/UserInfo/Search?format=json";
        Request requests = new Request.Builder().url(url).post(body).build();
        try {
            responses = client.newCall(requests).execute();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return responses;
    }
}
