package com.weverse.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
<<<<<<< HEAD

=======
>>>>>>> 8b6fb4b9b280173a45025a868ae4dc674cf7c9de
@EntityScan(basePackages = "com.weverse.sb")
public class WeverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeverseApplication.class, args);
	}

}
