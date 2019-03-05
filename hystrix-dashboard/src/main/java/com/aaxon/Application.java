package com.aaxon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author elvis
 */
@SpringBootApplication
@EnableHystrixDashboard
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
