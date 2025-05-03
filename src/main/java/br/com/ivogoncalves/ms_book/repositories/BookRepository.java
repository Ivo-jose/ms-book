package br.com.ivogoncalves.ms_book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ivogoncalves.ms_book.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{}
