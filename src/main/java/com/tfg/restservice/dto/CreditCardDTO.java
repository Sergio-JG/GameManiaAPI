package com.tfg.restservice.dto;

import java.sql.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreditCardDTO {

	private UUID user;

	private String cardNumber;

	private String cardHolderName;

	private Date expirationDate;

	private String cvv;

	private String billingAddress;

}