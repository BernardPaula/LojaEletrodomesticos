package com.bernardpaula.lojaEletrodomesticos.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bernardpaula.lojaEletrodomesticos.service.DataBaseService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DataBaseService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		dbService.instantiateDatabase();
		return true;
	}

}
