package com.tfg.restservice.controller;

import java.text.ParseException;
import java.sql.Date;
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

import com.tfg.restservice.dto.SaleDetailDTO;
import com.tfg.restservice.error.NotFoundException;
import com.tfg.restservice.model.SaleDetail;
import com.tfg.restservice.repository.SaleDetailRepository;
import com.tfg.restservice.service.DateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class SaleDetailController {

	private final SaleDetailRepository SaleDetailService;
	private final DateService dateService;

	/**
	 * Obtenemos todos los saleos
	 *
	 * @return
	 */

	@GetMapping("/saledetail")
	public ResponseEntity<Object> obtenerTodos() {

		List<SaleDetail> result = SaleDetailService.findAll();

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
	@GetMapping("/saledetail/{id}")
	public SaleDetail obtenerUno(@PathVariable UUID id) {

		return SaleDetailService.findById(id).orElseThrow(() -> new NotFoundException(id));

	}

	/**
	 * Obtenemos un saleo en base a su ID
	 *
	 * @param startDate La fecha de inicio
	 * @param endDate   La fecha de fin
	 * @return Una lista de ventas entre las dos fechas
	 */

	@GetMapping("/saledetail/betweenDates")
	public ResponseEntity<List<SaleDetail>> getSaleDetailsBetweenDates(@RequestBody Map<String, String> dates) {

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

		List<SaleDetail> sales = SaleDetailService.findAll();
		return new ResponseEntity<>(sales, HttpStatus.OK);
	}

	/**
	 * Insertamos un nuevo saleoX
	 *
	 * @param nuevo
	 * @return saleo insertado
	 */

	@PostMapping("/saledetail")
	public ResponseEntity<SaleDetail> newSaleDetail(@RequestBody SaleDetail newG) {

		SaleDetail newSaleDetail = new SaleDetail();

		return ResponseEntity.status(HttpStatus.CREATED).body(SaleDetailService.save(newSaleDetail));
	}

	/**
	 *
	 * @param editar
	 * @param id
	 * @return
	 */

	@PutMapping("/saledetail/{id}")
	public SaleDetail editarSaleDetailo(@RequestBody SaleDetailDTO editar, @PathVariable UUID id) {

		return SaleDetailService.findById(id).map(p -> {

			return SaleDetailService.save(p);
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

	@DeleteMapping("/saledetail/{id}")
	public ResponseEntity<Object> borrarSaleDetailo(@PathVariable UUID id) {
		SaleDetail sale = SaleDetailService.findById(id).orElseThrow(() -> new NotFoundException(id));

		SaleDetailService.delete(sale);
		return ResponseEntity.noContent().build();
	}

}