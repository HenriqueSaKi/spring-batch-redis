package br.com.springbatchredis;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Batch + Redis", version = "0.0.1", description = "Service to read file and store data into redis or SQL Server + Batch scheduling"))
@EnableScheduling
public class SpringBatchRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchRedisApplication.class, args);
	}

}
