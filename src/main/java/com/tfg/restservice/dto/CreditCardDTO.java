package com.tfg.restservice.dto;

import java.sql.Date;

import com.tfg.restservice.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreditCardDTO {

	private User user;

	private String cardHolder;

	private String cardNumber;

	private Date expirationDate;

	private String cvv;

	private String billingAddress;

}