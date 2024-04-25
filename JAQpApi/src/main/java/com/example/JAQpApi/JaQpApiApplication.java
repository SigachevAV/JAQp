package com.example.JAQpApi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.JAQpApi.DTO.AuthenticationRequest;
import com.example.JAQpApi.Service.AuthService;
import com.example.JAQpApi.Service.QuizService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class JaQpApiApplication
{
	private static final Logger logger = LoggerFactory.getLogger(JaQpApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(JaQpApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(	AuthService service, QuizService quizService ) {
		return args -> {
			AuthenticationRequest adminAuth = AuthenticationRequest.builder()
					.username("admin")
					.password("admin")
					.build();
			System.out.println("Admin token: " + service.authenticate(adminAuth).getJwtToken());
		};
	}
}
