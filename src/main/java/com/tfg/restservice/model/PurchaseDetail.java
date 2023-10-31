package com.tfg.restservice.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "purchase_detail")
@Getter
@Setter
public class PurchaseDetail {

	@Id
	@GeneratedValue
	@Column(name = "purchase_detail_id")
	private UUID purchaseItemID;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "unit_price")
	private BigDecimal unitPrice;

	@Column(name = "subtotal")
	private BigDecimal subtotal;

	@ManyToOne
	@JoinColumn(name = "purchase_id")
	private Purchase purchase;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

}
