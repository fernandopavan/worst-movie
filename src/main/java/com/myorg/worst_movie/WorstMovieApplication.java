package com.myorg.worst_movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class WorstMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorstMovieApplication.class, args);
	}

}
