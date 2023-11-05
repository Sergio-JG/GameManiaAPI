package com.tfg.restservice.dto;

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

public class GameGenreDTO {

	private UUID gameGenreId;

	private List<GameDTO> game;

	private List<GenreDTO> genre;

}