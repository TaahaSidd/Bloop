package com.spicalabs.bloop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class BloopApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BloopApplication.class, args);
	}

}
