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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity

@Table(name = "review")

@Getter
@Setter

public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private UUID reviewId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Transient
	private String username;

	public String getUsername() {
		if (game != null) {
			return user.getUsername();
		} else {
			return null;
		}
	}

	@ManyToOne
	@JoinColumn(name = "game_id")
	private Game game;

	@Transient
	private String gameName;

	public String getGameName() {
		if (game != null) {
			return game.getTitle();
		} else {
			return null;
		}
	}

	@Column(name = "score")
	private int rating;

	@Column(name = "comment")
	private String comment;

}
