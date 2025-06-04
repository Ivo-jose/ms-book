package br.com.ivogoncalves.ms_book.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.ivogoncalves.ms_book.dtos.BookDTO;
import br.com.ivogoncalves.ms_book.proxy.CurrencyExchangeProxy;
import br.com.ivogoncalves.ms_book.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {

	private final String template = "Book PORT: { %s } - Currency Exchange PORT: { %s }";
	@Autowired
	private Environment environment;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BookRepository repository;
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	
	public BookDTO findBook(long id, String currency) {
		log.info("Find Book by id: {} and currency: {}", id, currency);
		var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found! ID: " + id));
		var currencyExchange = proxy.getCurrencyExchange(entity.getPrice(),	"USD", currency);
		entity.setPrice(currencyExchange.getConvertedValue());
		entity.setCurrency(currency);
		entity.setEnvironment(String.format(template, environment.getProperty("local.server.port"), currencyExchange.getEnvironment()));
		return mapper.map(entity, BookDTO.class);
	}
}
