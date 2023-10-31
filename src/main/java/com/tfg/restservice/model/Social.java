
package com.tfg.restservice.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "social")

@Getter
@Setter

public class Social {

	@Id
	@GeneratedValue
	@Column(name = "user_social_id")
	private UUID socialId;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "steam_url")
	private String steamUrl;

	@Column(name = "twitch_url")
	private String twitchUrl;

	@Column(name = "youtube_url")
	private String youtubeUrl;

	@Column(name = "discord_tag")
	private String discordTag;

}
