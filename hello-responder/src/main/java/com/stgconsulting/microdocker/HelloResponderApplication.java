package com.stgconsulting.microdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(HelloResponderChannels.class)
public class HelloResponderApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloResponderApplication.class, args);
	}
}
