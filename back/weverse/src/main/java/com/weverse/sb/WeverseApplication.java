package com.weverse.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WeverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeverseApplication.class, args);
	}

}
