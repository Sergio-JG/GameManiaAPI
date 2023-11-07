package com.tfg.restservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.restservice.dto.PurchaseDetailDTO;
import com.tfg.restservice.dtoconverter.PurchaseDetailDTOConverter;
import com.tfg.restservice.error.NotFoundException;
import com.tfg.restservice.model.PurchaseDetail;
import com.tfg.restservice.repository.PurchaseDetailRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PurchaseDetailController {

	private final PurchaseDetailRepository purchaseDetailRepository;
	private final PurchaseDetailDTOConverter purchaseDetailDTOConverter;

	@GetMapping("/purchaseDetail")
	public ResponseEntity<Object> obtainAll() {
		List<PurchaseDetail> result = purchaseDetailRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
		} else {
			List<PurchaseDetailDTO> dtoList = result.stream().map(purchaseDetailDTOConverter::convertToDto).toList();
			return ResponseEntity.ok(dtoList);
		}
	}

	@GetMapping("/purchaseDetail/{id}")
	public ResponseEntity<Object> obtainOne(@PathVariable UUID id) {
		Optional<PurchaseDetail> result = purchaseDetailRepository.findById(id);

		if (result.isEmpty()) {
			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} else {
			PurchaseDetailDTO purchaseDetailDTO = purchaseDetailDTOConverter.convertToDto(result.get());
			return ResponseEntity.ok(purchaseDetailDTO);
		}
	}

	@PostMapping("/purchaseDetail")
	public ResponseEntity<Object> addPurchaseDetail(@RequestBody PurchaseDetailDTO purchaseDetailDTO) {
		PurchaseDetail newPurchaseDetail = purchaseDetailDTOConverter.convertToEntity(purchaseDetailDTO);
		newPurchaseDetail = purchaseDetailRepository.save(newPurchaseDetail);
		PurchaseDetailDTO createdPurchaseDetailDTO = purchaseDetailDTOConverter.convertToDto(newPurchaseDetail);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPurchaseDetailDTO);
	}

	@DeleteMapping("/purchaseDetail/{id}")
	public ResponseEntity<Object> deletePurchaseDetail(@PathVariable UUID id) {
		PurchaseDetail purchaseDetail = purchaseDetailRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
		purchaseDetailRepository.delete(purchaseDetail);
		return ResponseEntity.noContent().build();
	}
}
