package com.reference.commitment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackages = "com.reference.commitment.repository")
@SpringBootApplication//(scanBasePackages={"com.reference.commitment", "com.reference.commitment.controller", "com.reference.commitment.repository"})
public class CommitmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommitmentApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CommitmentApplication.class);
	}


}
