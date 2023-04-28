package com.hellobirdie.chatflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ChatflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatflowApplication.class, args);
	}

}
