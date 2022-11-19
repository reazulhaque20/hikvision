package com.apex.hikvision.controller;

import com.apex.hikvision.configuration.ClientConfig;
import okhttp3.*;
import org.apache.catalina.authenticator.DigestAuthenticator;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("check/hikvision")
public class TestController {

    RestTemplate restTemplate;



    @GetMapping("/connect")
    String hikvisionConnect() throws IOException {
//        String body = "{\n" +
//                "   \"AcsEventCond\":{\n" +
//                "      \"searchID\":\"3242s34\",\n" +
//                "      \"searchResultPosition\":0,\n" +
//                "      \"numOfMatches\": 300,\n" +
//                "      \"maxResults\":600,\n" +
//                "      \"major\":0,\n" +
//                "      \"minor\":0,\n" +
//                "      \"startTime\":\"2022-09-09T17:30:08+08:00\",\n" +
//                "      \"endTime\":\"2022-12-12T17:30:08+08:00\"\n" +
//                "   }\n" +
//                "}";
//        JSONObject request = new JSONObject();
//        request.put("body", body);
//        String uri = "http://192.168.4.24/ISAPI/AccessControl/AcsEvent?format=json";
//        try {
//            ResponseEntity<JSONObject> entity = restTemplate.postForEntity(uri, request, JSONObject.class);
//        }catch (Exception exception){
//            System.out.println(exception);
//        }

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String credential = Credentials.basic("admin", "Hik12345");
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n   \"AcsEventCond\":{\r\n      \"searchID\":\"3242s34\",\r\n      \"searchResultPosition\":0,\r\n      \"numOfMatches\": 300,\r\n      \"maxResults\":600,\r\n      \"major\":0,\r\n      \"minor\":0,\r\n      \"startTime\":\"2022-09-09T17:30:08+08:00\",\r\n      \"endTime\":\"2022-12-12T17:30:08+08:00\"\r\n   }\r\n}");
        Request request = new Request.Builder()
                .url("http://192.168.4.24/ISAPI/AccessControl/AcsEvent?format=json")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", credential)
                .build();
        Response response = client.newCall(request).execute();
        return "Connected";
    }
}
