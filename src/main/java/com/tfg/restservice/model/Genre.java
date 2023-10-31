package com.tfg.restservice.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
@Table(name = "genre")

@Getter
@Setter

public class Genre {

	@Id
	@GeneratedValue
	@Column(name = "genre_id")
	private UUID genreId;

	@Column(name = "genre_name")
	private String genreName;

	@ManyToMany(mappedBy = "genres")
	@JsonIgnore
	private List<Game> games;

}
