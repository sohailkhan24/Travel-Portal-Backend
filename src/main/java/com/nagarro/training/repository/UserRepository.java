package com.nagarro.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.training.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUserName(String userName);

	User findByEmail(String email);
}
