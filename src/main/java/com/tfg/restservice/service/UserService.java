package com.tfg.restservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tfg.restservice.model.User;
import com.tfg.restservice.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(UUID userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void delete(UUID userId) {
		userRepository.deleteById(userId);
	}
}
