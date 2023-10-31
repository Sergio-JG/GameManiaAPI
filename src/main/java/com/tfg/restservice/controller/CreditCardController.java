package com.tfg.restservice.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.restservice.dto.CreditCardDTO;
import com.tfg.restservice.dtoconverter.CreditCardDTOConverter;
import com.tfg.restservice.error.NotFoundException;
import com.tfg.restservice.model.CreditCard;
import com.tfg.restservice.repository.CreditCardRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class CreditCardController {

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	private CreditCardDTOConverter creditCardDTOConverter;

	/**
	 * Obtain all CreditCard
	 *
	 * @return
	 */

	@GetMapping("/creditcard")

	public ResponseEntity<Object> obtainAll() {

		List<CreditCard> result = creditCardRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
		} else {
			List<CreditCardDTO> dtoList = result.stream().map(creditCardDTOConverter::convertToDto)
					.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Obtain CreditCard via ID
	 *
	 * @param id
	 * @return Null if not found
	 *
	 */

	@GetMapping("/creditcard/{id}")
	public ResponseEntity<Object> obtainOne(@PathVariable UUID id) {

		Optional<CreditCard> result = creditCardRepository.findById(id);

		if (result.isEmpty()) {
			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} else {

			List<CreditCardDTO> dtoList = result.stream().map(creditCardDTOConverter::convertToDto)
					.collect(Collectors.toList());

			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Insert CreditCard
	 *
	 * @param New
	 * @return New CreditCard inserted
	 */

	@PostMapping("/creditcard")
	public ResponseEntity<Object> newCreditCard(@RequestBody CreditCardDTO cc) {

		CreditCard cc1 = new CreditCard();

		cc1.setUser(cc1.getUser());
		cc1.setCardNumber(cc1.getCardNumber());
		cc1.setCardHolderName(cc1.getCardHolderName());
		cc1.setExpirationDate(cc1.getExpirationDate());
		cc1.setCvv(cc1.getCvv());
		cc1.setBillingAddress(cc1.getBillingAddress());

		return ResponseEntity.status(HttpStatus.CREATED).body(creditCardRepository.save(cc1));
	}

	/**
	 *
	 * @param editar
	 * @param id
	 * @return
	 */

	@PutMapping("/creditcard/{id}")
	public ResponseEntity<Object> editCreditCard(@RequestBody CreditCard editar, @PathVariable UUID id) {

		Optional<CreditCard> result = creditCardRepository.findById(id);

		if (result.isEmpty()) {

			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

		} else {

			CreditCard cc = new CreditCard();

			cc.setUser(cc.getUser());
			cc.setCardNumber(cc.getCardNumber());
			cc.setCardHolderName(cc.getCardHolderName());

			cc.setExpirationDate(cc.getExpirationDate());
			cc.setCvv(cc.getCvv());
			cc.setBillingAddress(cc.getBillingAddress());
			return ResponseEntity.ok(creditCardRepository.save(cc));

		}
	}

	/**
	 *
	 * Borra un CreditCard del catálogo en base a su id
	 *
	 * @param id
	 * @return
	 *
	 */

	@DeleteMapping("/creditcard/{id}")
	public ResponseEntity<Object> deleteCreditCard(@PathVariable UUID id) {

		CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
		creditCardRepository.delete(creditCard);

		return ResponseEntity.noContent().build();
	}
}