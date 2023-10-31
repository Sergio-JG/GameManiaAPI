package com.tfg.restservice.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

@Service
public class PasswordHashingService {

	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;
	private static final int SALT_LENGTH = 16;

	public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = generateSalt(); // Generate a random salt
		return hashPassword(password, salt); // Call the hashPassword function with the password and salt
	}

	public static boolean verifyPassword(String password, String hashedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] decodedHash = Base64.getDecoder().decode(hashedPassword); // Decode the stored hash
		byte[] salt = extractSalt(decodedHash); // Extract the salt from the decoded hash

		// Compare the entered password's hash with the stored hash
		return hashedPassword.equals(hashPassword(password, salt));
	}

	private static byte[] generateSalt() {
		SecureRandom secureRandom = new SecureRandom(); // Initialize a secure random number generator
		byte[] salt = new byte[SALT_LENGTH]; // Create a byte array to store the salt
		secureRandom.nextBytes(salt); // Generate random bytes for the salt
		return salt; // Return the generated salt
	}

	private static String hashPassword(String password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH); // Set the key
																								// specification with
																								// password, salt,
																								// iterations, and key
																								// length
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256"); // Get an instance of the
																							// secret key factory
		byte[] hash = factory.generateSecret(spec).getEncoded(); // Generate the hash using the key specification

		// Combine salt and hash and encode as a Base64 string
		byte[] combined = combineSaltAndHash(salt, hash); // Combine the salt and hash arrays
		return Base64.getEncoder().encodeToString(combined); // Encode the combined array as a Base64 string
	}

	private static byte[] extractSalt(byte[] combined) {
		byte[] salt = new byte[SALT_LENGTH]; // Create a byte array to store the extracted salt
		System.arraycopy(combined, 0, salt, 0, SALT_LENGTH); // Copy the first SALT_LENGTH bytes from the combined array
																// to the salt array
		return salt; // Return the extracted salt
	}

	private static byte[] combineSaltAndHash(byte[] salt, byte[] hash) {
		byte[] combined = new byte[salt.length + hash.length]; // Create a byte array to store the combined salt and
																// hash
		System.arraycopy(salt, 0, combined, 0, salt.length); // Copy the salt array to the beginning of the combined
																// array
		System.arraycopy(hash, 0, combined, salt.length, hash.length); // Copy the hash array to the remaining part of
																		// the combined array
		return combined; // Return the combined array
	}
}
