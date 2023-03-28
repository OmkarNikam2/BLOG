package com.example.CRM_OMKAR;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrmOmkarApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmOmkarApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){return new ModelMapper();}

}
