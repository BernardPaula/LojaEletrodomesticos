package com.bernardpaula.LojaEletrodomesticos.configuration;

import com.bernardpaula.LojaEletrodomesticos.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DataBaseService dbService;
	
	@Value("spring.jpa.hibernate.ddl-auto")
	private String chave;
	
	@Bean
	public boolean instantiateDataBase() {
		if(!"create".equals(chave)) {
			return false;
		}
		
		dbService.instantiateDatabase();
		return true;
	}
	
}
