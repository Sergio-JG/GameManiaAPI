package com.tfg.restservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	private final PurchaseRepository PurchaseRepository;
	private final PurchaseDTOConverter PurchaseDTOConverter;

	/**
	 * Obtain all Purchase
	 *
	 * @return
	 */

	@GetMapping("/Purchase")
	public ResponseEntity<Object> obtainAll() {

		List<Purchase> result = PurchaseRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
		} else {

			List<PurchaseDTO> dtoList = result.stream().map(PurchaseDTOConverter::convertToDto)
					.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Obtain Purchase via ID
	 *
	 * @param id
	 * @return Null if not found
	 *
	 */

	@GetMapping("/Purchase/{id}")
	public ResponseEntity<Object> obtainOne(@PathVariable UUID id) {

		Optional<Purchase> result = PurchaseRepository.findById(id);

		if (result.isEmpty()) {
			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} else {

			List<PurchaseDTO> dtoList = result.stream().map(PurchaseDTOConverter::convertToDto)
					.collect(Collectors.toList());

			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Insert Purchase
	 *
	 * @param New
	 * @return New Purchase inserted
	 */

	@PostMapping("/Purchase")
	public ResponseEntity<Object> newPurchase(@RequestBody PurchaseDTO newC) {

		Purchase newPurchase = new Purchase();

		return ResponseEntity.status(HttpStatus.CREATED).body(PurchaseRepository.save(newPurchase));
	}

	/**
	 *
	 * @param editar
	 * @param id
	 * @return
	 */

	@PutMapping("/Purchase/{id}")
	public ResponseEntity<Object> editPurchase(@RequestBody Purchase editar, @PathVariable UUID id) {

		Optional<Purchase> result = PurchaseRepository.findById(id);

		if (result.isEmpty()) {

			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

		} else {

			Purchase pp = new Purchase();

			return ResponseEntity.ok(PurchaseRepository.save(pp));

		}

	}

	/**
	 *
	 * Borra un Purchaseo del catálogo en base a su id
	 *
	 * @param id
	 * @return
	 *
	 */

	@DeleteMapping("/Purchase/{id}")
	public ResponseEntity<Object> deletePurchase(@PathVariable UUID id) {

		Purchase Purchase = PurchaseRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
		PurchaseRepository.delete(Purchase);

		return ResponseEntity.noContent().build();
	}
}