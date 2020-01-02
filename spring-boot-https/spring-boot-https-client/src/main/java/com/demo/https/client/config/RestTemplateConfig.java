package com.demo.https.client.config;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;


/**
 * @Configuration org.springframework.context.annotation.Configuration spring的注解，用于表示这个类是配置类，spring提倡用配置类来替代 xml配置
 * */
@Configuration
public class RestTemplateConfig {

    /**
     * 建造者模式，你可以配置 restTemplateBuilder 后再build()
     * */
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    /**
     * @Bean org.springframework.context.annotation.Bean spring的注解，将方法返回的对象作为bean存储在IOC容器中
     * bean的name默认是方法名，可以通过 @Bean 的属性指定，这里bean的name="restTemplate"
     * 看HttpsClientController 中的属性命名和注入
     * */
    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }

    /**
     * 这里bean的name="httpsRestTemplate" 自己可以打断点一步步跟代码，加深理解
     * 每个类点进去看一下注释和实现的接口
     * 下面的目的就是做了https的一些准备，这些准备是为了封装成RestTemplate
     * */
    @Bean
    RestTemplate httpsRestTemplate() throws Exception {
        // HttpComponentsClientHttpRequestFactory 工厂模式，各种配置嘛，http请求工厂
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(5 * 60 * 1000);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(5 * 60 * 1000);
        httpComponentsClientHttpRequestFactory.setReadTimeout(5 * 60 * 1000);
        // SSLContextBuilder 建造者模式，用于创建SSLContext
        // 1.加载https.keystore  2.读取https.keystore中的密钥（这里就是公钥）
        SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        ClassPathResource resource = new ClassPathResource("https.keystore");
        InputStream inputStream = resource.getInputStream();
        keyStore.load(inputStream, new char[]{'c', 'h', 'a', 'n', 'g', 'e', 'i', 't'});
        inputStream.close();
        // TODO SSLConnectionSocketFactory 和 Registry
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build(), NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslConnectionSocketFactory).build();
        // PoolingHttpClientConnectionManager http连接池
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
        poolingHttpClientConnectionManager.setMaxTotal(200);
        // 建造者模式，上面工厂，配置准备好后，就创建http连接客户端，这里是CloseableHttpClient
        CloseableHttpClient closeableHttpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).setConnectionManager(poolingHttpClientConnectionManager).setConnectionManagerShared(true).build();
        // 有了http连接客户端，把http连接客户端设置到 http请求工厂
        httpComponentsClientHttpRequestFactory.setHttpClient(closeableHttpClient);
        // 创建RestTemplate
        RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);
        // 更换RestTemplate 默认的注册的一些HttpMessageConverter，HttpMessageConverter是http返回数据进行处理的接口定义
        // 启动有个StringHttpMessageConverter默认是使用ISO-8859-1，下面代码就新建一个UTF_8的StringHttpMessageConverter替换掉这个，避免中文乱码
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        ArrayList<HttpMessageConverter<?>> convertersValid = new ArrayList<>();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof StringHttpMessageConverter) {
                StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
                stringHttpMessageConverter.setWriteAcceptCharset(true);
                converter = stringHttpMessageConverter;
            }
            convertersValid.add(converter);
        }
        restTemplate.setMessageConverters(convertersValid);

        return restTemplate;
    }

}
