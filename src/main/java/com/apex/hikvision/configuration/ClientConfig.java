package com.apex.hikvision.configuration;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        HttpHost host = new HttpHost("localhost", 8080, "http");
        CloseableHttpClient client = HttpClientBuilder.create().
                setDefaultCredentialsProvider(provider()).useSystemProperties().build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactoryDigestAuth(host, client);

        return new RestTemplate(requestFactory);
    }

    private CredentialsProvider provider() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials =
                new UsernamePasswordCredentials("admin", "Hik12345");
        provider.setCredentials(AuthScope.ANY, credentials);
        return provider;
    }
}
