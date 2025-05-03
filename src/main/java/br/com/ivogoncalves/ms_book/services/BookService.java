package br.com.ivogoncalves.ms_book.services;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.ivogoncalves.ms_book.dtos.BookDTO;
import br.com.ivogoncalves.ms_book.repositories.BookRepository;
import br.com.ivogoncalves.ms_book.response.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {

	private final String template = "PORT: { %s }";
	@Autowired
	private Environment environment;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BookRepository repository;
	
	public BookDTO findBook(long id, String currency) {
		log.info("Find Book by id: {} and currency: {}", id, currency);
		var entity = repository.findById(id).orElseThrow(() -> 
											new RuntimeException("Book not found! ID: " + id));
		HashMap<String, String> params = new HashMap<>();
		params.put("amount", String.valueOf(entity.getPrice()));
		params.put("from", "USD");
		params.put("to", currency);
		var currencyExchange = new RestTemplate()
		  .getForEntity("http://localhost:8000/api/currency-exchange/{amount}/{from}/{to}", 
				  			CurrencyExchange.class, params);
		entity.setEnvironment(String.format(template, environment.getProperty("local.server.port")));
		entity.setPrice(currencyExchange.getBody().getConvertedValue());
		entity.setCurrency(currency);
		return mapper.map(entity, BookDTO.class);
	}
}
