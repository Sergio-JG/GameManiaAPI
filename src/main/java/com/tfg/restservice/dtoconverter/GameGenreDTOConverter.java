package com.tfg.restservice.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tfg.restservice.dto.GameGenreDTO;
import com.tfg.restservice.model.GameGenre;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GameGenreDTOConverter {

	private final ModelMapper modelMapper;

	@PostConstruct
	public void configureMapper() {
		modelMapper.createTypeMap(GameGenre.class, GameGenreDTO.class)
				.addMapping(src -> src.getGame().getGameId(), GameGenreDTO::setGameGenreId)
				.addMapping(src -> src.getGenre().getGenreId(), GameGenreDTO::setGameGenreId);
	}

	public GameGenreDTO convertToDto(GameGenre gameGenre) {
		return modelMapper.map(gameGenre, GameGenreDTO.class);
	}

	public GameGenre convertToEntity(GameGenreDTO gameGenreDTO) {
		return modelMapper.map(gameGenreDTO, GameGenre.class);
	}
}
