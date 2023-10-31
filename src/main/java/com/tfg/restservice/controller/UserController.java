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

public class UserController  {

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
	public ResponseEntity<Object> newUser(@RequestBody UserDTO newC) {

		User newUser = new User();

		newUser.setUsername(newC.getUsername());

		String hashedPassword = "FAIL";

		try {
			hashedPassword = PasswordHashingService.hashPassword(newC.getPassword());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			String errorMessage = "Error occurred while hashing the password: " + e.getMessage();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}

		newUser.setPassword(hashedPassword);
		newUser.setFirstName(newC.getFirstName());
		newUser.setLastName(newC.getLastName());
		newUser.setEmail(newC.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(newUser));
	}

	/**
	 *
	 * @param editar
	 * @param id
	 * @return
	 */

	@PutMapping("/user/{id}")
	public ResponseEntity<Object> editUser(@RequestBody User editar, @PathVariable UUID id) {

		Optional<User> result = userRepository.findById(id);

		if (result.isEmpty()) {

			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

		} else {

			User u = new User();

			u.setUserId(id);
			u.setFirstName(editar.getFirstName());
			u.setLastName(editar.getLastName());
			u.setEmail(editar.getEmail());

			if (editar.getProfilePic() != null && !editar.getProfilePic().isEmpty()) {

				u.setProfilePic(editar.getProfilePic());
			}

			if (editar.getPassword() != null && !editar.getPassword().isEmpty()) {

				String hashedPassword = "FAIL";

				try {
					hashedPassword = PasswordHashingService.hashPassword(editar.getPassword());
				} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					String errorMessage = "Error occurred while hashing the password: " + e.getMessage();
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
				}

				u.setPassword(hashedPassword);
			}

			return ResponseEntity.ok(userRepository.save(u));

		}
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