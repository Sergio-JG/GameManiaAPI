package com.tfg.restservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

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

public class PurchaseDetailDTO {

	private UUID purchaseDetailId;

	private GameDTO game;

	private int quantity;

	private BigDecimal unitPrice;

	private BigDecimal subtotal;
}