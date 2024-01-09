package com.snowflake.team_8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Team8Application {

	public static void main(String[] args) {
		SpringApplication.run(Team8Application.class, args);
	}

}
