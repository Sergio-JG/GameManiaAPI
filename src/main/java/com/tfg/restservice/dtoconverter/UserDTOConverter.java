package com.tfg.restservice.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tfg.restservice.dto.UserDTO;
import com.tfg.restservice.model.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class UserDTOConverter {

	private final ModelMapper modelMapper;

	public UserDTO convertToDto(User User) {
		return modelMapper.map(User, UserDTO.class);

	}

	public User convertToEntity(UserDTO UserDTO) {
		return modelMapper.map(UserDTO, User.class);
	}

}