package com.tfg.restservice.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "provider")

@Getter
@Setter

public class Provider {

	@Id
	@GeneratedValue
	@Column(name = "provider_id")
	private UUID providerId;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@OneToMany(mappedBy = "provider")
	@JsonIgnore
	private List<Account> account;

	@OneToMany(mappedBy = "provider")
	@JsonIgnore
	private List<Purchase> purchases;

}
