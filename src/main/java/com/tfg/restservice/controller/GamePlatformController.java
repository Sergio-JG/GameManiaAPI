package com.tfg.restservice.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.restservice.error.NotFoundException;
import com.tfg.restservice.model.Game;
import com.tfg.restservice.model.GamePlatform;
import com.tfg.restservice.model.Platform;
import com.tfg.restservice.repository.GamePlatformRepository;
import com.tfg.restservice.repository.GameRepository;
import com.tfg.restservice.repository.PlatformRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class GamePlatformController {

	private final GamePlatformRepository gamePlatformRepository;
	private final GameRepository gameRepository;
	private final PlatformRepository platformRepository;

	/**
	 * Obtain all gamePlatform
	 *
	 * @return
	 */

	@GetMapping("/gameplatform")
	public ResponseEntity<Object> obtainAll() {

		List<GamePlatform> result = gamePlatformRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
		} else {

			return ResponseEntity.ok(result);
		}
	}

	/**
	 * Obtain gamePlatform via ID
	 *
	 * @param id
	 * @return Null if not found
	 *
	 */

	@GetMapping("/gameplatform/{id}")
	public ResponseEntity<Object> obtainOne(@PathVariable UUID id) {

		Optional<GamePlatform> result = gamePlatformRepository.findById(id);

		if (result.isEmpty()) {
			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} else {

			return ResponseEntity.ok(result);
		}
	}

	/**
	 * Insert GamePlatform
	 *
	 * @param New
	 * @return New GamePlatform inserted
	 */

	@PostMapping("/gameplatform")

	public ResponseEntity<Object> newGamePlatform(@RequestBody Map<String, UUID> requestBody) {

		UUID gameId = requestBody.get("gameId");
		UUID platformId = requestBody.get("platformId");

		Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException(gameId));
		Platform platform = platformRepository.findById(platformId)
				.orElseThrow(() -> new NotFoundException(platformId));

		GamePlatform newGamePlatform = new GamePlatform();

		newGamePlatform.setGame(game);
		newGamePlatform.setPlatform(platform);

		return ResponseEntity.status(HttpStatus.CREATED).body(gamePlatformRepository.save(newGamePlatform));
	}

	/**
	 *
	 * @param editar
	 * @param id
	 * @return
	 */

	@PutMapping("/gameplatform/{id}")
	public ResponseEntity<Object> editGamePlatform(@RequestBody Map<String, UUID> requestBody, @PathVariable UUID id) {

		Optional<GamePlatform> result = gamePlatformRepository.findById(id);

		if (result.isEmpty()) {

			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

		} else {

			UUID gameId = requestBody.get("gameId");
			UUID platformId = requestBody.get("platformId");

			Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException(gameId));
			Platform platform = platformRepository.findById(platformId)
					.orElseThrow(() -> new NotFoundException(platformId));

			GamePlatform update = new GamePlatform();

			// update.setId(id);
			update.setGame(game);
			update.setPlatform(platform);

			return ResponseEntity.ok(gamePlatformRepository.save(update));

		}
	}

	/**
	 *
	 * Borra un gamePlatformo del catálogo en base a su id
	 *
	 * @param id
	 * @return
	 *
	 */

	@DeleteMapping("/gameplatform/{id}")
	public ResponseEntity<Object> deleteGamePlatform(@PathVariable UUID id) {

		GamePlatform gamePlatform = gamePlatformRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
		gamePlatformRepository.delete(gamePlatform);

		return ResponseEntity.noContent().build();
	}
}