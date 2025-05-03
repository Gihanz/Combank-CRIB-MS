package com.nable.crib.comn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "CRIB Common Util Operations API", version = "1.0", description = "N-able - Crib Automation Common Util REST Services"))
public class CommonUtilsMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonUtilsMSApplication.class, args);
	}

}
