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

public class ReviewDTO {

	private String username;

	private String gameName;

	private int rating;

	private String comment;

}
