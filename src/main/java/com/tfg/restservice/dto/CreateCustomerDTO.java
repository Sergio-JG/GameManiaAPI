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
public class CreateCustomerDTO {

	private String firstName;

	private String lastName;

	private String email;

	private String username;

	private String password;
}
