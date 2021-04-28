package com.naveed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MicroserviceHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceHandlerApplication.class, args);
	}

}
