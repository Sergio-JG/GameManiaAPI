package com.tfg.restservice.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "user")

@Getter
@Setter

public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private UUID userId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "profile_pic")
	private String profilePic;

	@Column(name = "phone")
	private String phone;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private Social social;

	@OneToOne(mappedBy = "user")
	@JsonIgnore
	private CreditCard creditCard;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Review> reviews;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Sale> sales;

}
