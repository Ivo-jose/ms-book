package br.com.ivogoncalves.ms_book.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}
}
