package com.company.nations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class NationsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NationsProjectApplication.class, args);
	}

}
