package br.com.ivogoncalves.ms_book.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivogoncalves.ms_book.dtos.BookDTO;
import br.com.ivogoncalves.ms_book.services.BookService;

@RestController
@RequestMapping("/api/book-service")
public class BookController {
	
	@Autowired
	private BookService service;

	@GetMapping(value = "/{id}/{currency}")
	public ResponseEntity<BookDTO> findBook(@PathVariable Long id, @PathVariable String currency) {
		return ResponseEntity.ok(service.findBook(id, currency));
	}
}
