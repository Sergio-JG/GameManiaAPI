package com.tfg.restservice.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tfg.restservice.dto.PurchaseDetailDTO;
import com.tfg.restservice.dto.PurchaseDetailDTO;
import com.tfg.restservice.model.PurchaseDetail;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class PurchaseDetailDTOConverter {

	private final ModelMapper modelMapper;

	public PurchaseDetailDTO convertToDto(PurchaseDetail purchaseDetail) {
		PurchaseDetailDTO purchaseDetailDto = modelMapper.map(purchaseDetail, PurchaseDetailDTO.class);

		if (purchaseDetail.getGame() != null) {
			purchaseDetailDto.setGameName(purchaseDetail.getGame().getTitle());
			purchaseDetailDto.setGameId(purchaseDetail.getGame().getGameId());
		}

		return purchaseDetailDto;
	}

	public PurchaseDetailDTO convertToEntity(PurchaseDetailDTO purchaseDTO) {
		return modelMapper.map(purchaseDTO, PurchaseDetailDTO.class);
	}

}