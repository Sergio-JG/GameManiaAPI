package com.tfg.restservice.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class GameDTO {

	private UUID gameId;

	private String title;

	private float price;

	private String description;

	private Date releaseDate;

	private int numberOfSales;

	private BigDecimal totalScore;

	private List<GenreDTO> genres;

	private List<PlatformDTO> platforms;

	private List<ReviewDTO> reviews;
}