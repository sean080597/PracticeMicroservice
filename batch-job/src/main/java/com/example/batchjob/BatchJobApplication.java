package com.example.batchjob;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.owasp.security.logging.util.SecurityUtil;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableBatchProcessing
@EnableEurekaClient
@ComponentScan(basePackages = { "com.example.batchjob" })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class BatchJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchJobApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			SecurityUtil.logMessage("Let's inspect the beans provided by Spring Boot:");
			String[] beanNames = ctx.getBeanDefinitionNames();
			List<String> beanLs = Arrays.stream(beanNames).sorted().collect(Collectors.toList());
			SecurityUtil.logMessage(beanLs + "");
		};
	}

}
