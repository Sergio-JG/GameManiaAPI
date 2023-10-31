package com.tfg.restservice.controller;

import java.util.List;
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
import com.tfg.restservice.model.Review;
import com.tfg.restservice.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class ReviewController  {

	private final ReviewRepository reviewRepository;
	// private final ReviewDTOConverter reviewDTOConverter;

	/**
	 * Obtenemos todos los reviewos
	 *
	 * @return
	 */
	@GetMapping("/review")
	public ResponseEntity<Object> obtenerTodos() {

		List<Review> result = reviewRepository.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado datos en el catálogo");
		} else {
			// List<ReviewDTO> dtoList =
			// result.stream().map(reviewDTOConverter::convertToDto)
			// .collect(Collectors.toList());

			return ResponseEntity.ok(result);
		}
	}

	/**
	 * Obtenemos un reviewo en base a su ID
	 *
	 * @param id
	 * @return Null si no encuentra el reviewo
	 */
	@GetMapping("/review/{id}")
	public Review obtenerUno(@PathVariable UUID id) {

		return reviewRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

	}

	/**
	 * Insertamos un nuevo reviewoX
	 *
	 * @param nuevo
	 * @return reviewo insertado
	 */

	@PostMapping("/review")
	public ResponseEntity<Review> newReview(@RequestBody Review newG) {

		Review newReview = new Review();
		newReview.setGame(newG.getGame());
		newReview.setGame(newG.getGame());

		return ResponseEntity.status(HttpStatus.CREATED).body(reviewRepository.save(newReview));
	}

	/**
	 *
	 * @param editar
	 * @param id
	 * @return
	 */

	@PutMapping("/review/{id}")
	public Review editarReviewo(@RequestBody Review editar, @PathVariable UUID id) {

		return reviewRepository.findById(id).map(p -> {

			p.setGame(editar.getGame());

			return reviewRepository.save(p);
		}).orElseThrow(() -> new NotFoundException(id));
	}

	/**
	 *
	 * Borra un reviewo del catálogo en base a su id
	 *
	 * @param id
	 * @return
	 *
	 */

	@DeleteMapping("/review/{id}")
	public ResponseEntity<Object> borrarReviewo(@PathVariable UUID id) {
		Review review = reviewRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

		reviewRepository.delete(review);
		return ResponseEntity.noContent().build();
	}

}