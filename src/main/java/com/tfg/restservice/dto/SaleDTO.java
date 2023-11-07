package com.tfg.restservice.dto;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

import com.tfg.restservice.model.SaleDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter

public class SaleDTO {

	private UUID saleId;

	private UserDTO customer;

	private Date saleDate;

	private Set<SaleDetail> saleItems;

	private float totalAmount;

}