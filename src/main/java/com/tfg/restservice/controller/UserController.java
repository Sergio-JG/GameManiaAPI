package com.tfg.restservice.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

import com.tfg.restservice.dto.UserDTO;
import com.tfg.restservice.dtoconverter.UserDTOConverter;
import com.tfg.restservice.error.NotFoundException;
import com.tfg.restservice.model.User;
import com.tfg.restservice.repository.UserRepository;
import com.tfg.restservice.service.PasswordHashingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class UserController {

	private final UserRepository userRepository;
	private final UserDTOConverter userDTOConverter;

	/**
	 * Obtain all user
	 *
	 * @return
	 */

	@GetMapping("/user")
	public ResponseEntity<Object> obtainAll() {

		List<User> result = userRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
		} else {

			List<UserDTO> dtoList = result.stream().map(userDTOConverter::convertToDto).collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Obtain user via ID
	 *
	 * @param id
	 * @return Null if not found
	 *
	 */

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> obtainOne(@PathVariable UUID id) {

		Optional<User> result = userRepository.findById(id);

		if (result.isEmpty()) {
			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} else {

			List<UserDTO> dtoList = result.stream().map(userDTOConverter::convertToDto).collect(Collectors.toList());

			return ResponseEntity.ok(dtoList);
		}
	}

	/**
	 * Insert User
	 *
	 * @param New
	 * @return New User inserted
	 */

	@PostMapping("/user")
	public ResponseEntity<Object> newUser(@RequestBody UserDTO userData) {

		User user = userDTOConverter.convertToEntity(userData);

		String hashedPassword = "FAIL";

		try {
			hashedPassword = PasswordHashingService.hashPassword(user.getPassword());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			String errorMessage = "Error occurred while hashing the password: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}

		user.setPassword(hashedPassword);

		return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
	}

	/**
	 *
	 * @param editar
	 * @param id
	 * @return
	 */

	@PutMapping("/user/{id}")
	public ResponseEntity<Object> editUser(@RequestBody UserDTO userData, @PathVariable UUID id) {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		User existingUser = optionalUser.get();

		existingUser.setUsername(userData.getUsername());
		existingUser.setPassword(userData.getPassword());
		existingUser.setFirstName(userData.getFirstName());
		existingUser.setLastName(userData.getLastName());
		existingUser.setEmail(userData.getEmail());
		existingUser.setProfilePic(userData.getProfilePic());
		existingUser.setPhone(userData.getPhone());
		existingUser.setUserId(id);

		User updatedUser = userRepository.save(existingUser);
		return ResponseEntity.ok(updatedUser);
	}

	/**
	 *
	 * Borra un usero del catálogo en base a su id
	 *
	 * @param id
	 * @return
	 *
	 */

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {

		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
		userRepository.delete(user);

		return ResponseEntity.noContent().build();
	}
}