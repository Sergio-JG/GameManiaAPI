package com.tfg.restservice.dto;

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

public class SaleDetailDTO {

	private UserDTO customer;

	private GameDTO game;

	private int quantity;

	private float subtotal;

}