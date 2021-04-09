package com.cybersoft.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.cybersoft")
@EnableJpaRepositories("com.cybersoft.repository")
@EntityScan("com.cybersoft.entity")
public class ELearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningApplication.class, args);
	}

}
