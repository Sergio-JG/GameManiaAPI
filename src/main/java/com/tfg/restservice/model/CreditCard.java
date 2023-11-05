package com.tfg.restservice.model;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "credit_card")

@Getter
@Setter
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "credit_card_id")
	private UUID creditCardId;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "cardholder_name")
	private String cardHolderName;

	@Column(name = "expiration_date")
	private Date expirationDate;

	@Column(name = "cvv")
	private String cvv;

	@Column(name = "billing_address")
	private String billingAddress;

}
