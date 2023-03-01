package com.bernardpaula.LojaEletrodomesticos.configuration;

import com.bernardpaula.LojaEletrodomesticos.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DataBaseService dbService;
	
	
	@Bean
	public boolean instantiateDataBase() {
		
		dbService.instantiateDatabase();
		return true;
		
	}
	
}
