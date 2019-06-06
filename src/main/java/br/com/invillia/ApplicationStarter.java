package br.com.invillia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.social.config.annotation.EnableSocial;


@EnableSocial
@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
@EntityScan(basePackages = "br.com.invillia.entity")
public class ApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
	}
	
}
