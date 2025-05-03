package br.com.ivogoncalves.ms_book.proxy;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ivogoncalves.ms_book.response.CurrencyExchange; 

@FeignClient(name = "ms-currency-exchange", url = "${ms-currency-exchange.url}")
public interface CurrencyExchangeProxy {

	@GetMapping(value = "/api/currency-exchange/{amount}/{from}/{to}")
	public CurrencyExchange getCurrencyExchange(@PathVariable BigDecimal amount,
													@PathVariable String from, @PathVariable String to); 
}
