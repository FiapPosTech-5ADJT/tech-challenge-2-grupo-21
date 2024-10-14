package br.com.fiap.park_tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ParkTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkTechApplication.class, args);
	}

}
