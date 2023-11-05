package com.tfg.restservice.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sale_detail")
@Getter
@Setter

public class SaleDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "sale_item_id")
	private UUID saleItemID;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "subtotal")
	private BigDecimal subtotal;

	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;
}
