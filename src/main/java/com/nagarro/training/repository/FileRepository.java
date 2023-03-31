package com.nagarro.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.training.model.File;
import com.nagarro.training.model.Ticket;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
	List<File> findByticket(Ticket ticket);
}
