package br.com.ivogoncalves.ms_book.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 180)
	private String author;
	@Column(nullable = false, length = 250)
	private String title;
	@Column(name = "launch_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date launchDate;
	@Column(nullable = false)
	private BigDecimal price;
	@Transient
	private String currency;
	@Transient
	private String environment;
}
