package com.tfg.restservice.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.tfg.restservice.model.Provider;

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

public class PurchaseDTO {

	private Provider provider;

	private Date purchaseDate;

	private BigDecimal totalAmount;

}