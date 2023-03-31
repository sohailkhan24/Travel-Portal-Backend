package com.nagarro.training.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.training.dto.LoginUserDto;
import com.nagarro.training.dto.RegisterUserDto;
import com.nagarro.training.model.User;
import com.nagarro.training.repository.UserRepository;
import com.nagarro.training.security.Jwt;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserServiceImpl userDetailsServiceImpl;
	@Autowired
	private Jwt jwt;
	@Autowired
	private Jwt jwtutil;
	@Autowired
	private JavaMailSender javaMailSender;

//	Sign Up
	public RegisterUserDto signup(RegisterUserDto registerRequest) {
		User user = new User(registerRequest.getFirstName(), registerRequest.getLastName(),
				registerRequest.getBuisnessUnit(), registerRequest.getTitle(), registerRequest.getEmail(),
				registerRequest.getTelephone(), registerRequest.getAddress1(), registerRequest.getAddress2(),
				registerRequest.getCity(), registerRequest.getState(), registerRequest.getZip(),
				registerRequest.getCountry());
		final String password = generatePassword();
		user.setUserName(user.getEmail());
		user.setPassword(encodePassword(password));
		user = userRepository.save(user);
//		sendEmail(user.getEmail(), user.getUserName(), password);
		sendEmail("khanna.piyush30@gmail.com", user.getUserName(), password);
		registerRequest.setId(user.getId());
		return registerRequest;
	}

//	Login user
	public String login(LoginUserDto loginRequest) throws Exception {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authenticate);
			final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUserName());
			User user = userRepository.findByUserName(loginRequest.getUserName()).get();
			if (user.getRole().equals("user")) {
				final String jwtToken = jwt.generateToken(userDetails);
				return jwtToken;
			} else {
				return "invalid";
			}
		} catch (Exception e) {
			return "invalid";
		}
	}

//	Forgot Password
	public boolean forgotPassword(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			final String password = generatePassword();
			user.setPassword(encodePassword(password));
			userRepository.save(user);
//			sendEmail(user.getEmail(), user.getUserName(), password);
			sendEmail("khanna.piyush30@gmail.com", user.getUserName(), password);
			return true;
		}
		return false;
	}

//	Update user
	public RegisterUserDto update(RegisterUserDto registerRequest) {
		if (userRepository.existsById(registerRequest.getId())) {
			User userInDb = userRepository.findById(registerRequest.getId()).get();
			User user = new User(registerRequest.getId(), registerRequest.getFirstName(), registerRequest.getLastName(),
					registerRequest.getBuisnessUnit(), registerRequest.getTitle(), registerRequest.getEmail(),
					registerRequest.getTelephone(), registerRequest.getAddress1(), registerRequest.getAddress2(),
					registerRequest.getCity(), registerRequest.getState(), registerRequest.getZip(),
					registerRequest.getCountry());
			user.setUserName(registerRequest.getEmail());
			user.setTickets(userInDb.getTickets());
			final String password = generatePassword();
			user.setPassword(encodePassword(password));
			userRepository.save(user);
//			sendEmail(user.getEmail(), user.getUserName(), password);
			sendEmail("khanna.piyush30@gmail.com", user.getUserName(), password);
			return registerRequest;
		}
		return null;
	}

//	Admin Login
	public String adminLogin(LoginUserDto loginRequest) throws Exception {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authenticate);
			final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUserName());
			User user = userRepository.findByUserName(loginRequest.getUserName()).get();
			if (user.getRole().equals("admin")) {
				final String jwtToken = jwt.generateToken(userDetails);
				return jwtToken;
			} else {
				return "invalid";
			}
		} catch (Exception e) {
			return "invalid";
		}
	}

	private void sendEmail(String email, String username, String password) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Nagarro Travel Portal Information");
		message.setText(
				"You have requested your user name and password for the your access to the Nagarro Travel Portal :\n\n"
						+ "Usename: " + username + "\r\n" + "Password: " + password + "\r\n\n"
						+ "Please contact the Travel team if you have any questions.\r\n\n" + "Thank you,\r\n"
						+ "Nagarro Travel Team.");
		javaMailSender.send(message);
	}

	private String generatePassword() {
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 8;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphaNumeric.length());
			char randomChar = alphaNumeric.charAt(index);
			sb.append(randomChar);
		}
		String password = sb.toString();
		return password;
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	public boolean ifAdminByHeader(String header) {
		String jwt = header.substring(7);
		String username = jwtutil.extractUsername(jwt);
		User user = userRepository.findByUserName(username).get();
		if (user.getRole().equals("admin"))
			return true;
		return false;
	}

	public boolean ifAdminByUsername(String username) {
		User user = userRepository.findByUserName(username).get();
		if (user != null && user.getRole().equals("admin"))
			return true;
		return false;
	}

	public User getUser(String header) {
		String jwt = header.substring(7);
		String username = jwtutil.extractUsername(jwt);
		User user = userRepository.findByUserName(username).get();
		return user;
	}
}
