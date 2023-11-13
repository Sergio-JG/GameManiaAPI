package com.tfg.restservice.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "role")

@Getter
@Setter

public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "role_id")
	private UUID roleId;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<User> users;

}
