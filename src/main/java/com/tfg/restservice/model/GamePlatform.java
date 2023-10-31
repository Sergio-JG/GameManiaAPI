package com.tfg.restservice.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

@Table(name = "game_platform")

@Getter
@Setter

public class GamePlatform {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "game_platform_id")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "platform_id")
	private Platform platform;

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

}