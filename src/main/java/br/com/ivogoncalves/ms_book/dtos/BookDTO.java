package br.com.ivogoncalves.ms_book.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String author;
	private String title;
	private Date launchDate;
	private BigDecimal price;
	private String currency;
	private String environment;

}
