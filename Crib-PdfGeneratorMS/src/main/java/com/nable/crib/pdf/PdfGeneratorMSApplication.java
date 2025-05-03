package com.nable.crib.pdf;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.nable.crib.pdf.util.RequestResponseLoggingInterceptor;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "CRIB PDF Generator API", version = "1.0", description = "N-able - Crib Automation PDF Generator REST Services"))
public class PdfGeneratorMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfGeneratorMSApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
		
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new RequestResponseLoggingInterceptor());
        
        RestTemplate rt =  builder
	            			.setConnectTimeout(Duration.ofMillis(60000))
	            			.setReadTimeout(Duration.ofMillis(60000))
	            			.interceptors(interceptors)
	            			.basicAuthentication("nable1", "nable@123")
	            			.build();
		
		rt.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		
		return rt;
    }

}
