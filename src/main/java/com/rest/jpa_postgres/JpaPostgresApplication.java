package com.rest.jpa_postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JpaPostgresApplication {

	public static void main(String[] args) {
		 ApplicationContext ctx = SpringApplication.run(JpaPostgresApplication.class, args);

		 System.out.println("Spring boot server started on post 8080");
	}

}
