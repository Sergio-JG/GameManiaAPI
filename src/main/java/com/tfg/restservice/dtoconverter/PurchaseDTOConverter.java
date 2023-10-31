package com.tfg.restservice.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tfg.restservice.dto.PurchaseDTO;
import com.tfg.restservice.model.Purchase;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class PurchaseDTOConverter {

	private final ModelMapper modelMapper;

	public PurchaseDTO convertToDto(Purchase Purchase) {
		return modelMapper.map(Purchase, PurchaseDTO.class);

	}

	public Purchase convertToEntity(PurchaseDTO PurchaseDTO) {
		return modelMapper.map(PurchaseDTO, Purchase.class);
	}

}