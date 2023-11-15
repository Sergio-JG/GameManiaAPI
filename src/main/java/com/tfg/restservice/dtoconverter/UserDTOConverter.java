package com.tfg.restservice.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.tfg.restservice.dto.UserDTO;
import com.tfg.restservice.model.User;
import com.tfg.restservice.repository.CreditCardRepository;
import com.tfg.restservice.repository.RoleRepository;
import com.tfg.restservice.repository.SocialRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class UserDTOConverter {

	private final ModelMapper modelMapper;

	private final RoleRepository roleRepository;
	private final SocialRepository socialRepository;
	private final CreditCardRepository creditCardRepository;

	public UserDTO convertToDto(User User) {
		return modelMapper.map(User, UserDTO.class);

	}

	public User convertToEntity(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user.setRole(roleRepository.findById(userDTO.getRoleId()).orElse(null));
		return user;
	}

}