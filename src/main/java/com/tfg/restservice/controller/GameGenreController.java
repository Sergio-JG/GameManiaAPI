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
import com.tfg.restservice.model.GameGenre;
import com.tfg.restservice.model.Genre;
import com.tfg.restservice.repository.GameGenreRepository;
import com.tfg.restservice.repository.GameRepository;
import com.tfg.restservice.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class GameGenreController {

	private final GameGenreRepository gameGenreRepository;
	private final GameRepository gameRepository;
	private final GenreRepository genreRepository;

	/**
	 * Obtain all gameGenre
	 *
	 * @return
	 */

	@GetMapping("/gamegenre")
	public ResponseEntity<Object> obtainAll() {

		List<GameGenre> result = gameGenreRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
		} else {

			return ResponseEntity.ok(result);
		}
	}

	/**
	 * Obtain gameGenre via ID
	 *
	 * @param id
	 * @return Null if not found
	 *
	 */

	@GetMapping("/gamegenre/{id}")
	public ResponseEntity<Object> obtainOne(@PathVariable UUID id) {

		Optional<GameGenre> result = gameGenreRepository.findById(id);

		if (result.isEmpty()) {
			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} else {

			return ResponseEntity.ok(result);
		}
	}

	/**
	 * Insert GameGenre
	 *
	 * @param New
	 * @return New GameGenre inserted
	 */

	@PostMapping("/gamegenre")

	public ResponseEntity<Object> newGameGenre(@RequestBody Map<String, UUID> requestBody) {

		UUID gameId = requestBody.get("gameId");
		UUID genreId = requestBody.get("genreId");

		Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException(gameId));
		Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new NotFoundException(genreId));

		GameGenre newGameGenre = new GameGenre();

		newGameGenre.setGame(game);
		newGameGenre.setGenre(genre);

		return ResponseEntity.status(HttpStatus.CREATED).body(gameGenreRepository.save(newGameGenre));
	}

	/**
	 *
	 * @param editar
	 * @param id
	 * @return
	 */

	@PutMapping("/gamegenre/{id}")
	public ResponseEntity<Object> editGameGenre(@RequestBody Map<String, UUID> requestBody, @PathVariable UUID id) {

		Optional<GameGenre> result = gameGenreRepository.findById(id);

		if (result.isEmpty()) {

			NotFoundException exception = new NotFoundException(id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());

		} else {

			UUID gameId = requestBody.get("gameId");
			UUID genreId = requestBody.get("genreId");

			Game game = gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException(gameId));
			Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new NotFoundException(genreId));

			GameGenre update = new GameGenre();

			// update.setId(id);
			update.setGame(game);
			update.setGenre(genre);

			return ResponseEntity.ok(gameGenreRepository.save(update));

		}
	}

	/**
	 *
	 * Borra un gameGenreo del catálogo en base a su id
	 *
	 * @param id
	 * @return
	 *
	 */

	@DeleteMapping("/gamegenre/{id}")
	public ResponseEntity<Object> deleteGameGenre(@PathVariable UUID id) {

		GameGenre gameGenre = gameGenreRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
		gameGenreRepository.delete(gameGenre);

		return ResponseEntity.noContent().build();
	}
}