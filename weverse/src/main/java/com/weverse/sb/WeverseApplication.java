package com.weverse.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.weverse.sb.entity")
public class WeverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeverseApplication.class, args);
	}

}
