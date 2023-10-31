package com.tfg.restservice.dto;

import com.tfg.restservice.model.User;

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

public class CreateSocialDTO {

	private User customer;

	private String steamUrl;

	private String twitchUrl;

	private String youtubeUrl;

	private String discordTag;
}
