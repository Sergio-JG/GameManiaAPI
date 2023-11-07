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

import com.tfg.restservice.dto.SaleDTO;
import com.tfg.restservice.dtoconverter.SaleDTOConverter;
import com.tfg.restservice.error.NotFoundException;
import com.tfg.restservice.model.Sale;
import com.tfg.restservice.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SaleController {

	private final SaleRepository saleRepository;
	private final SaleDTOConverter saleDTOConverter;

	@GetMapping("/sale")
	public ResponseEntity<Object> obtainAll() {
		List<Sale> result = saleRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
		} else {
			List<SaleDTO> dtoList = result.stream().map(saleDTOConverter::convertToDto).toList();
			return ResponseEntity.ok(dtoList);
		}
	}

	@GetMapping("/sale/{id}")
	public ResponseEntity<Object> obtainOne(@PathVariable UUID id) {
		Optional<Sale> result = saleRepository.findById(id);

		if (result.isEmpty()) {
			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} else {
			SaleDTO saleDTO = saleDTOConverter.convertToDto(result.get());
			return ResponseEntity.ok(saleDTO);
		}
	}

	@PostMapping("/sale")
	public ResponseEntity<Object> addSale(@RequestBody SaleDTO saleDTO) {
		Sale newSale = saleDTOConverter.convertToEntity(saleDTO);
		newSale = saleRepository.save(newSale);
		SaleDTO createdSaleDTO = saleDTOConverter.convertToDto(newSale);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdSaleDTO);
	}

	@DeleteMapping("/sale/{id}")
	public ResponseEntity<Object> deleteSale(@PathVariable UUID id) {
		Sale sale = saleRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
		saleRepository.delete(sale);
		return ResponseEntity.noContent().build();
	}
}
