package com.tfg.restservice.dto;

import java.util.UUID;

import com.tfg.restservice.model.Social;

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

public class UserDTO {

	private UUID userId;

	private String firstName;

	private String lastName;

	private String email;

	private String username;

	private String password;

	private Social social;
}
