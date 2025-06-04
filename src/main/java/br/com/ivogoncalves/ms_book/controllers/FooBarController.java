package br.com.ivogoncalves.ms_book.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/book-service")
@Slf4j
public class FooBarController {

	@GetMapping("/foo-bar")
	//@Retry(name = "foo-bar", fallbackMethod = "fallbackFooBar")
	//@CircuitBreaker(name = "default", fallbackMethod = "fallbackFooBar")
	//@RateLimiter(name = "default")
	@Bulkhead(name = "default")
	public String fooBar() {
		log.info("Request to foo-bar endpoint received");
		String nonExistsHost = "http://localhost:8080/foo-bar";
		//var response = new RestTemplate().getForEntity(nonExistsHost, String.class);
		
		//return response.getBody();
		
		return "Foo-Bar!!!";
	}
	
	public String fallbackFooBar(Exception e) {
		log.error("Fallback method called due to: {}", e.getMessage());
		return "Fallback response: Service is currently unavailable. Please try again later.";
	}
}
