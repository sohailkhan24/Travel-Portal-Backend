package com.nagarro.training.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.training.dto.LoginUserDto;
import com.nagarro.training.dto.RegisterUserDto;
import com.nagarro.training.service.AuthService;
import com.nagarro.training.utility.ResponseMessage;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	@Autowired
	AuthService authService;

//	USER SIGN UP
	@RequestMapping(value = "/users/signup", method = RequestMethod.POST)
	public ResponseEntity<RegisterUserDto> signUp(@Valid @RequestBody RegisterUserDto registerRequest) {
		try {
			RegisterUserDto user = authService.signup(registerRequest);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

//  USER LOGIN
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> login(@RequestBody LoginUserDto loginRequest) {
		try {
			String jwtToken = authService.login(loginRequest);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(jwtToken));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("invalid"));
		}
	}

//	FORGOT PASSWORD
	@RequestMapping(value = "/users/forgot-password", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> forgotPassword(@RequestParam("email") String email) {
		boolean forgotPassword = authService.forgotPassword(email);
		if (forgotPassword)
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("success"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Email cannot be send."));

	}

//	USER UPDATE
	@RequestMapping(value = "/users/update", method = RequestMethod.PUT)
	public ResponseEntity<RegisterUserDto> update(@Valid @RequestBody RegisterUserDto registerRequest) {
		RegisterUserDto updateUser = authService.update(registerRequest);
		if (updateUser != null) {
			return ResponseEntity.status(HttpStatus.OK).body(updateUser);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

//	ADMIN LOGIN
	@RequestMapping(value = "/users/admin-login", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessage> adminLogin(@RequestBody LoginUserDto loginRequest) {
		try {
			String jwtToken = authService.adminLogin(loginRequest);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(jwtToken));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("invalid"));
		}
	}
}
