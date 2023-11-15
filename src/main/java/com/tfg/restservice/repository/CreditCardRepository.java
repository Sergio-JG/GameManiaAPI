package com.tfg.restservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.restservice.model.CreditCard;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {

	// List<CreditCard> findByUserId(UUID userId);

}