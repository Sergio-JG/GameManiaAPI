package com.tfg.restservice.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tfg.restservice.dto.SaleDTO;
import com.tfg.restservice.model.Sale;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class SaleDTOConverter {

	private final ModelMapper modelMapper;

	public SaleDTO convertToDto(Sale game) {
		return modelMapper.map(game, SaleDTO.class);

	}

	public Sale convertToEntity(SaleDTO gameDTO) {
		return modelMapper.map(gameDTO, Sale.class);
	}

}