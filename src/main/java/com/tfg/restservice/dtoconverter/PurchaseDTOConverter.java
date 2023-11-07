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

	public PurchaseDTO convertToDto(Purchase purchase) {
		return modelMapper.map(purchase, PurchaseDTO.class);

	}

	public Purchase convertToEntity(PurchaseDTO purchaseDTO) {
		return modelMapper.map(purchaseDTO, Purchase.class);
	}

}