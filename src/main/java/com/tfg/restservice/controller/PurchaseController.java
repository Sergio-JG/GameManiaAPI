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

import com.tfg.restservice.dto.PurchaseDTO;
import com.tfg.restservice.dtoconverter.PurchaseDTOConverter;
import com.tfg.restservice.error.NotFoundException;
import com.tfg.restservice.model.Purchase;
import com.tfg.restservice.repository.PurchaseRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

	private final PurchaseRepository purchaseRepository;
	private final PurchaseDTOConverter purchaseDTOConverter;

	@GetMapping("/purchase")
	public ResponseEntity<Object> obtainAll() {
		List<Purchase> result = purchaseRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
		} else {
			List<PurchaseDTO> dtoList = result.stream().map(purchaseDTOConverter::convertToDto).toList();
			return ResponseEntity.ok(dtoList);
		}
	}

	@GetMapping("/purchase/{id}")
	public ResponseEntity<Object> obtainOne(@PathVariable UUID id) {
		Optional<Purchase> result = purchaseRepository.findById(id);

		if (result.isEmpty()) {
			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} else {
			PurchaseDTO purchaseDTO = purchaseDTOConverter.convertToDto(result.get());
			return ResponseEntity.ok(purchaseDTO);
		}
	}

	@PostMapping("/purchase")
	public ResponseEntity<Object> addPurchase(@RequestBody PurchaseDTO purchaseDTO) {
		Purchase newPurchase = purchaseDTOConverter.convertToEntity(purchaseDTO);
		newPurchase = purchaseRepository.save(newPurchase);
		PurchaseDTO createdPurchaseDTO = purchaseDTOConverter.convertToDto(newPurchase);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPurchaseDTO);
	}

	@DeleteMapping("/purchase/{id}")
	public ResponseEntity<Object> deletePurchase(@PathVariable UUID id) {
		Purchase purchase = purchaseRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
		purchaseRepository.delete(purchase);
		return ResponseEntity.noContent().build();
	}
}
