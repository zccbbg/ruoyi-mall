package com.cyl.config;


import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    //从连接池获取连接的超时时间，一般设置为较短；
    @Value("${http-pool.connection-request-timeout}")
    private int connectionRequestTimeout;

    //连接超时时间
    @Value("${http-pool.connection-timeout}")
    private int connectionTimeout;

    //完成连接后，socket通信超时时间
    @Value("${http-pool.socket-timeout}")
    private int socketTimeout;

    //单host（可以理解为单域名）最大并发数
    @Value("${http-pool.max-per-route}")
    private int maxPerRoute;

    //连接池最大连接数
    @Value("${http-pool.max-total}")
    private int maxTotal;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(httpRequestFactory());
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        //设置整个连接池最大连接数 根据自己的场景决定
        connectionManager.setMaxTotal(maxTotal);
        //路由是对maxTotal的细分
        connectionManager.setDefaultMaxPerRoute(maxPerRoute);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)//服务器返回数据(response)的时间，超过该时间抛出read timeout
                .setConnectTimeout(connectionTimeout)//连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
                .setConnectionRequestTimeout(connectionRequestTimeout)//从连接池中获取连接的超时时间，超过该时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }
}
