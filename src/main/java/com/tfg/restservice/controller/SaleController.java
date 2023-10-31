package com.tfg.restservice.controller;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
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

import com.tfg.restservice.dto.SaleDTO;
import com.tfg.restservice.error.NotFoundException;
import com.tfg.restservice.model.Sale;
import com.tfg.restservice.repository.SaleRepository;
import com.tfg.restservice.service.DateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class SaleController {

	private final SaleRepository SaleService;
	private final DateService dateService;

	/**
	 * Obtenemos todos los saleos
	 *
	 * @return
	 */

	@GetMapping("/sale")
	public ResponseEntity<Object> obtenerTodos() {

		List<Sale> result = SaleService.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado datos en el catálogo");
		} else {
			return ResponseEntity.ok(result);
		}
	}

	/**
	 * Obtenemos un saleo en base a su ID
	 *
	 * @param id
	 * @return Null si no encuentra el saleo
	 */
	@GetMapping("/sale/{id}")
	public Sale obtenerUno(@PathVariable UUID id) {

		return SaleService.findById(id).orElseThrow(() -> new NotFoundException(id));

	}

	/**
	 * Obtenemos un saleo en base a su ID
	 *
	 * @param startDate La fecha de inicio
	 * @param endDate   La fecha de fin
	 * @return Una lista de ventas entre las dos fechas
	 */

	@GetMapping("/sale/betweenDates")
	public ResponseEntity<List<Sale>> getSalesBetweenDates(@RequestBody Map<String, String> dates) {

		Date startDate = null;
		try {
			startDate = dateService.fromStringToSQLDate(dates.get("startDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date endDate = null;
		try {
			endDate = dateService.fromStringToSQLDate(dates.get("endDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Sale> sales = SaleService.findBySaleDateBetween(startDate, endDate);
		return new ResponseEntity<>(sales, HttpStatus.OK);
	}

	/**
	 * Insertamos un nuevo saleoX
	 *
	 * @param nuevo
	 * @return saleo insertado
	 */

	@PostMapping("/sale")
	public ResponseEntity<Sale> newSale(@RequestBody Sale newG) {

		Sale newSale = new Sale();

		return ResponseEntity.status(HttpStatus.CREATED).body(SaleService.save(newSale));
	}

	/**
	 *
	 * @param editar
	 * @param id
	 * @return
	 */

	@PutMapping("/sale/{id}")
	public Sale editarSaleo(@RequestBody SaleDTO editar, @PathVariable UUID id) {

		return SaleService.findById(id).map(p -> {

			return SaleService.save(p);
		}).orElseThrow(() -> new NotFoundException(id));
	}

	/**
	 *
	 * Borra un saleo del catálogo en base a su id
	 *
	 * @param id
	 * @return
	 *
	 */

	@DeleteMapping("/sale/{id}")
	public ResponseEntity<Object> borrarSaleo(@PathVariable UUID id) {
		Sale sale = SaleService.findById(id).orElseThrow(() -> new NotFoundException(id));

		SaleService.delete(sale);
		return ResponseEntity.noContent().build();
	}

}