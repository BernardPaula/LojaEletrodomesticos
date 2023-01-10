package com.bernardpaula.lojaEletrodomesticos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	//Url do Swagger  -   swagger-ui.html
	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.useDefaultResponseMessages(false)   //Na documentação terá algumas respostas padrão -> então vc pode autera-las
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.bernardpaula.lojaEletrodomesticos.rest.controller")) //Fala como irá buscar as apis que irão aparecer na documentação
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo());
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("LojaEletrodomesticos API")
			.description("Projeto de uma loja e eletrodomesticos")
			.version("1.0")
			.contact(contact())
			.build();
	}
	
	private Contact contact() {   //Dados do contato
		return new Contact("Bernard Paula", "https://github.com/BernardPaula",
						"bernardpauladev3@gmail.com");
	}

	
}
