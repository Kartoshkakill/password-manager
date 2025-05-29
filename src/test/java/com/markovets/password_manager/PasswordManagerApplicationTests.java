//package com.markovets.password_manager;
//
//import com.markovets.password_manager.dto.AuthRequest;
//import com.markovets.password_manager.dto.PasswordDTO;
//import com.markovets.password_manager.dto.UserDTO;
//import com.markovets.password_manager.model.Password;
//import com.markovets.password_manager.model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import java.util.List;
//import java.util.Map;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class PasswordManagerApplicationTests {
//
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	private String jwtToken;
//
//	@BeforeEach
//	void setUp() {
//		// Register a test user
//		UserDTO userDTO = new UserDTO("testuser", "test@example.com", "password123");
//		ResponseEntity<User> registerResponse = restTemplate.postForEntity("/api/users/register", userDTO, User.class);
//		assertEquals(HttpStatus.OK, registerResponse.getStatusCode());
//
//		// Login to get JWT token
//		AuthRequest authRequest = new AuthRequest("testuser", "password123");
//		ResponseEntity<Map> loginResponse = restTemplate.postForEntity("/api/auth/login", authRequest, Map.class);
//		assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
//		Map<String, String> responseBody = loginResponse.getBody();
//		assertNotNull(responseBody, "Login response body should not be null");
//		assertNotNull(responseBody.get("token"), "JWT token should not be null");
//		jwtToken = "Bearer " + responseBody.get("token");
//	}
//
//	@Test
//	void testUserRegistration() {
//		UserDTO newUserDTO = new UserDTO("testuser2", "test2@example.com", "password123");
//		ResponseEntity<User> response = restTemplate.postForEntity("/api/users/register", newUserDTO, User.class);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertNotNull(response.getBody().getId());
//	}
//
//	@Test
//	void testUserLoginSuccess() {
//		AuthRequest authRequest = new AuthRequest("testuser", "password123");
//		ResponseEntity<Map> response = restTemplate.postForEntity("/api/auth/login", authRequest, Map.class);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertNotNull(response.getBody().get("token"));
//	}
//
//	@Test
//	void testUserLoginFailure() {
//		AuthRequest authRequest = new AuthRequest("testuser", "wrongpassword");
//		ResponseEntity<Map> response = restTemplate.postForEntity("/api/auth/login", authRequest, Map.class);
//		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
//	}
//
//	@Test
//	void testAddPassword() {
//		PasswordDTO passwordDTO = new PasswordDTO("example.com", "user", "secret123");
//		HttpEntity<PasswordDTO> entity = new HttpEntity<>(passwordDTO, createHeaders());
//		ResponseEntity<Password> response = restTemplate.exchange("/api/passwords/1", HttpMethod.POST, entity, Password.class);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertNotNull(response.getBody().getId());
//	}
//
//	@Test
//	void testGetUserPasswords() {
//		HttpEntity<Void> entity = new HttpEntity<>(createHeaders());
//		ResponseEntity<List> response = restTemplate.exchange("/api/passwords/1", HttpMethod.GET, entity, List.class);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		assertNotNull(response.getBody());
//	}
//
////	@Test
////	void testUpdatePassword() {
////		// First add a password
////		PasswordDTO passwordDTO = new PasswordDTO("example.com", "user", "secret123");
////		HttpEntity<PasswordDTO> addEntity = new HttpEntity<>(passwordDTO, createHeaders());
////		ResponseEntity<Password> addResponse = restTemplate.exchange("/api/passwords/1", HttpMethod.POST, addEntity, Password.class);
////		assertEquals(HttpStatus.OK, addResponse.getStatusCode());
////		Long passwordId = addResponse.getBody().getId();
////
////		// Update the password
////		PasswordDTO updatedPasswordDTO = new PasswordDTO("example.com", "user", "newsecret456");
////		HttpEntity<PasswordDTO> updateEntity = new HttpEntity<>(updatedPasswordDTO, createHeaders());
////		ResponseEntity<Password> updateResponse = restTemplate.exchange("/api/passwords/" + passwordId, HttpMethod.PUT, updateEntity, Password.class);
////		assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
////		assertEquals("newsecret456", updateResponse.getBody().getPassword());
////	}
//
//	@Test
//	void testDeletePassword() {
//		// First add a password
//		PasswordDTO passwordDTO = new PasswordDTO("example.com", "user", "secret123");
//		HttpEntity<PasswordDTO> addEntity = new HttpEntity<>(passwordDTO, createHeaders());
//		ResponseEntity<Password> addResponse = restTemplate.exchange("/api/passwords/1", HttpMethod.POST, addEntity, Password.class);
//		assertEquals(HttpStatus.OK, addResponse.getStatusCode());
//		Long passwordId = addResponse.getBody().getId();
//
//		// Delete the password
//		HttpEntity<Void> deleteEntity = new HttpEntity<>(createHeaders());
//		ResponseEntity<Void> deleteResponse = restTemplate.exchange("/api/passwords/" + passwordId, HttpMethod.DELETE, deleteEntity, Void.class);
//		assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
//	}
//
//	private HttpHeaders createHeaders() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization", jwtToken);
//		return headers;
//	}
//}