package com.carlosamaral.dass21;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Dass21", version = "2.0", description = "Dass21"))
public class Dass21Application {

	public static  void main(String[] args) {
		SpringApplication.run(Dass21Application.class, args);
	}

}
