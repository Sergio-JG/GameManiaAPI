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

public class SocialDTO {

	private String steamUrl;

	private String twitchUrl;

	private String youtubeUrl;

	private String discordTag;

}