package com.tfg.restservice.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tfg.restservice.dto.GenreDTO;
import com.tfg.restservice.model.Genre;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class GenreDTOConverter {

	private final ModelMapper modelMapper;

	public GenreDTO convertToDto(Genre customer) {
		return modelMapper.map(customer, GenreDTO.class);

	}

	public Genre convertToEntity(GenreDTO customerDTO) {
		return modelMapper.map(customerDTO, Genre.class);
	}

}