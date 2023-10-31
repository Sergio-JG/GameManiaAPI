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

import com.tfg.restservice.dto.AccountDTO;
import com.tfg.restservice.dtoconverter.AccountDTOConverter;
import com.tfg.restservice.error.NotFoundException;
import com.tfg.restservice.model.Account;
import com.tfg.restservice.repository.AccountRepository;
import com.tfg.restservice.repository.ProviderRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class AccountController {

	private final AccountRepository accountRepository;
	private final AccountDTOConverter accountDTOConverter;
	private final ProviderRepository providerRepository;

	/**
	 * Obtain all Account
	 *
	 * @return
	 */

	@GetMapping("/account")

	public ResponseEntity<Object> obtainAll() {

		List<Account> result = accountRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
		} else {

			List<AccountDTO> dtoList = result.stream().map(accountDTOConverter::convertToDto)
					.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Obtain Account via ID
	 *
	 * @param id
	 * @return Null if not found
	 *
	 */

	@GetMapping("/account/{id}")
	public ResponseEntity<Object> obtainOne(@PathVariable UUID id) {

		Optional<Account> result = accountRepository.findById(id);

		if (result.isEmpty()) {
			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} else {

			List<AccountDTO> dtoList = result.stream().map(accountDTOConverter::convertToDto)
					.collect(Collectors.toList());

			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Insert Account
	 *
	 * @param New
	 * @return New Account inserted
	 */

	@PostMapping("/account")
	public ResponseEntity<Object> newAccount(@RequestBody AccountDTO na) {

		Account na1 = new Account();

		na1.setBankName(na.getBankName());
		na1.setAccountHolderName(na.getAccountHolderName());
		na1.setAccountNumber(na.getAccountNumber());
		na1.setBankAddress(na.getBankAddress());
		na1.setBankRoutingNumber(na.getBankRoutingNumber());
		na1.setAccountBalance(na.getAccountBalance());

		return ResponseEntity.status(HttpStatus.CREATED).body(accountRepository.save(na1));
	}

	/**
	 *
	 * @param editar
	 * @param id
	 * @return
	 */

	@PutMapping("/account/{id}")
	public ResponseEntity<Object> editAccount(@RequestBody Account editar, @PathVariable UUID id) {

		Optional<Account> result = accountRepository.findById(id);

		if (result.isEmpty()) {

			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

		} else {

			Account na = new Account();

			na.setProvider(na.getProvider());
			na.setAccountHolderName(na.getAccountHolderName());
			na.setAccountNumber(na.getAccountNumber());
			na.setBankName(na.getBankName());
			na.setBankAddress(na.getBankAddress());
			na.setBankRoutingNumber(na.getBankRoutingNumber());

			return ResponseEntity.ok(accountRepository.save(na));

		}
	}

	/**
	 *
	 * Borra un Account del catálogo en base a su id
	 *
	 * @param id
	 * @return
	 *
	 */

	@DeleteMapping("/account/{id}")
	public ResponseEntity<Object> deleteAccount(@PathVariable UUID id) {

		Account account = accountRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
		accountRepository.delete(account);

		return ResponseEntity.noContent().build();
	}
}