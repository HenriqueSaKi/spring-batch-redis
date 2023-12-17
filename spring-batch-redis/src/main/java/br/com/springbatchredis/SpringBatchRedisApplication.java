package br.com.springbatchredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBatchRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchRedisApplication.class, args);
	}

}
